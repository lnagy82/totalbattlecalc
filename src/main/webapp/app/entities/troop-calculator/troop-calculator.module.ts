import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TroopCalculatorComponent } from './troop-calculator.component';
import { TroopCalculatorRoutingModule } from './troop-calculator-routing.module';
import { SharedModule } from 'app/shared/shared.module';

@NgModule({
  imports: [SharedModule,
    TroopCalculatorRoutingModule,
    CommonModule
  ],
  declarations: [TroopCalculatorComponent]
})
export class TroopCalculatorModule { }
