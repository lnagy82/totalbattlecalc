import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { TroopCalculatorComponent } from './troop-calculator.component';

const troopCalculatorRoute: Routes = [
  {
    path: '',
    component: TroopCalculatorComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(troopCalculatorRoute)],
  exports: [RouterModule],
})
export class TroopCalculatorRoutingModule {}
