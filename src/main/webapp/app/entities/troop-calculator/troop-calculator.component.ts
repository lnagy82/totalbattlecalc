import { TroopCalculatorService } from './service/troop-calculator.service';
import { Component, OnInit } from '@angular/core';
import { IUnit, Unit } from '../unit/unit.model';
import { UnitService } from '../unit/service/unit.service';
import { HttpResponse } from '@angular/common/http';
import { BattleUnit, IBattleUnit } from '../battle-unit/battle-unit.model';
import { LocalStorageService } from 'ngx-webstorage';
import { formatNumber } from '@angular/common';

@Component({
  selector: 'jhi-troop-calculator',
  templateUrl: './troop-calculator.component.html',
  styleUrls: ['./troop-calculator.component.scss'],
})
export class TroopCalculatorComponent implements OnInit {
  possibleUnits?: IUnit[];
  battleUnits?: IBattleUnit[] = [];
  isLoading = false;
  leadership: string | undefined;
  strength: string | undefined;
  health: string | undefined;
  maxLeadership = 28000;
  bHealth = true;
  selectedUnit?: IUnit;

  constructor(
    protected unitService: UnitService,
    protected troopCalculatorService: TroopCalculatorService,
    private localStorageService: LocalStorageService
  ) {}

  ngOnInit(): void {
    this.unitService.query().subscribe(
      (res: HttpResponse<IUnit[]>) => {
        this.isLoading = false;
        this.battleUnits = this.localStorageService.retrieve('battleUnits') ?? [];
        this.possibleUnits = res.body ?? [];
        this.resetCorrupted();
        this.checkCorrupted();
        this.filterPossibleUnits();
        this.sortByName(this.possibleUnits);
        this.calculate();
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  trackId(index: number, item: IUnit): number {
    return item.id!;
  }

  gotoBattle(unit: Unit): void {
    if (!this.selectedUnit) {
      this.selectedUnit = unit;
    }

    this.battleUnits!.push(new BattleUnit(1, false, 0, 564.0, 0, this.selectedUnit));
    const index: number = this.possibleUnits!.indexOf(this.selectedUnit);
    this.possibleUnits!.splice(index, 1);
    this.selectedUnit = undefined;
    this.saveUnits();
  }

  gotoHome(battleUnit: any): void {
    const index: number = this.battleUnits!.indexOf(battleUnit);
    this.battleUnits!.splice(index, 1);
    this.possibleUnits!.push(battleUnit.unit);
    this.sortByName(this.possibleUnits);

    this.saveUnits();
  }

  calculate(): void {
    this.battleUnits!.forEach(unit => {
      unit.number = 0;
    });
    const troopCalculation = this.troopCalculatorService.getTroopsCalculation(this.battleUnits!, this.maxLeadership);
    this.battleUnits = troopCalculation.battleUnits;
    this.leadership = formatNumber(troopCalculation.leadership!, 'en', '1.0-0');
    this.strength = formatNumber(troopCalculation.strength!, 'en', '1.0-0');
    this.health = formatNumber(troopCalculation.health!, 'en', '1.0-0');
    this.saveUnits();
  }

  getAllHealth(unit: BattleUnit): number {
    return (unit.unit!.health! * unit.number * (unit.bHealth + 100.0)) / 100.0;
  }

  getUnitAvatarUrl(unit: Unit): string {
    return 'content/images/units/' + unit.imageUrl!;
  }

  sumLeadership(troops: Array<BattleUnit>): number {
    let leadership = 0;

    troops.forEach(unit => {
      leadership += unit.unit!.leadership! * unit.number;
    });

    return leadership;
  }

  private saveUnits(): void {
    this.localStorageService.store('battleUnits', this.battleUnits);
  }
  private sortByName(units?: IUnit[]): void {
    units!.sort(function (a, b) {
      const cTier = a.tier! === b.tier! ? 0 : a.tier! < b.tier! ? -1 : 1;
      const cName = a.name!.localeCompare(b.name!);

      return cTier || cName;
    });
  }

  private checkCorrupted(): void {
    this.battleUnits?.forEach(bu => {
      this.possibleUnits?.forEach(pu => {
        if (bu.unit!.id === pu.id!) {
          if (JSON.stringify(bu.unit!) === JSON.stringify(pu)) {
            //its ok
          } else {
            bu.corrupted = true;
          }
        }
      });
    });
  }

  private resetCorrupted(): void {
    this.battleUnits!.forEach(unit => {
      unit.corrupted = false;
    });
  }

  private filterPossibleUnits(): void {
    this.possibleUnits = this.possibleUnits!.filter(
      unit => !this.battleUnits!.map(battleUnit => battleUnit.unit!.id!).includes(unit.id!)
    );
  }
}
