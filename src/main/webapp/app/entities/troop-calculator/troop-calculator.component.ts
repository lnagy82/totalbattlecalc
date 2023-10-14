import { TroopCalculatorService } from './service/troop-calculator.service';
import { Component, OnInit } from '@angular/core';
import { IUnit } from '../unit/unit.model';
import { UnitService } from '../unit/service/unit.service';
import { HttpResponse } from '@angular/common/http';
import { BattleUnit, IBattleUnit } from '../battle-unit/battle-unit.model';
import { LocalStorageService } from 'ngx-webstorage';

@Component({
  selector: 'jhi-troop-calculator',
  templateUrl: './troop-calculator.component.html',
  styleUrls: ['./troop-calculator.component.scss'],
})
export class TroopCalculatorComponent implements OnInit {
  possibleUnits?: IUnit[];
  battleUnits?: IBattleUnit[] = [];
  isLoading = false;
  maxLeadership = 28000;
  bHealth = 0;
  selectedUnit?: IUnit;

  constructor
  (protected unitService: UnitService,
    protected troopCalculatorService: TroopCalculatorService,
    private localStorageService: LocalStorageService) {}

  ngOnInit(): void {
    this.unitService.query().subscribe(
      (res: HttpResponse<IUnit[]>) => {
        this.isLoading = false;
        this.battleUnits = this.localStorageService.retrieve('battleUnits') ?? [];
        this.possibleUnits = this.localStorageService.retrieve('possibleUnits');

        if(this.possibleUnits == null || this.possibleUnits.length === 0){
            this.possibleUnits = res.body ?? [];
            this.sortByName(this.possibleUnits);
        }
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  trackId(index: number, item: IUnit): number {
    return item.id!;
  }

  gotoBattle(): void {
    if (this.selectedUnit) {
      this.battleUnits!.push(new BattleUnit(1, 0, 564.0, this.bHealth, this.selectedUnit));
      const index: number = this.possibleUnits!.indexOf(this.selectedUnit);
      this.possibleUnits!.splice(index, 1);
      this.selectedUnit = undefined;

      this.saveUnits();
    }
    // eslint-disable-next-line no-console
    console.log(this.selectedUnit);
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
    this.battleUnits = this.troopCalculatorService.getTroops(this.battleUnits!, this.maxLeadership);
    this.saveUnits();
  }

  getAllHealth(unit: BattleUnit): number {
    return unit.unit!.health! * unit.number * ((unit.bHealth + 100.0) / 100.0);
  }

  private saveUnits(): void {
    this.localStorageService.store("possibleUnits", this.possibleUnits);
    this.localStorageService.store("battleUnits", this.battleUnits);
  }
  private sortByName(units?: IUnit[]): void {
    units!.sort(function (a, b) {
      if (a.name! < b.name!) {
        return -1;
      }
      if (a.name! > b.name!) {
        return 1;
      }
      return 0;
    });
  }
}
