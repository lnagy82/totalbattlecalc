import { Component, OnInit } from '@angular/core';
import { IUnit } from '../unit/unit.model';
import { UnitService } from '../unit/service/unit.service';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'jhi-troop-calculator',
  templateUrl: './troop-calculator.component.html',
  styleUrls: ['./troop-calculator.component.scss']
})
export class TroopCalculatorComponent implements OnInit {

  units?: IUnit[];
  isLoading = false;

  constructor(protected unitService: UnitService) {}

  // eslint-disable-next-line @angular-eslint/no-empty-lifecycle-method, @typescript-eslint/explicit-function-return-type
  ngOnInit() {
    this.unitService
      .query()
      .subscribe(
        (res: HttpResponse<IUnit[]>) => {
          this.isLoading = false;
          this.units = res.body ?? [];
        },
        () => {
          this.isLoading = false;
        }
      );
  }

  trackId(index: number, item: IUnit): number {
    return item.id!;
  }

}
