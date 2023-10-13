import { Injectable } from '@angular/core';
import { Unit } from 'app/entities/unit/unit.model';
import { HealthOptimizerService } from './health-optimizer.service';
import { BattleUnit } from 'app/entities/battle-unit/battle-unit.model';

@Injectable({
  providedIn: 'root',
})
export class TroopCalculatorService {
  constructor(protected healthOptimizerService: HealthOptimizerService) {}

  getTroops(troops: Array<BattleUnit>, leadership: number): Array<BattleUnit> {
    // List<BattleUnit> preferredUnits = PreferredUnitFactory.initializePreferredUnits();
//    const troops: Array<BattleUnit> = [];

    this.healthOptimizerService.optimizeTroopsHealth(troops, troops, leadership);

    //TroopPrinter.printTroops(troops);
    // println("sumLeadership: " + df.format(healthOptimizer.sumLeadership(troops)));
    // println("sumCost: " + df.format(healthOptimizer.sumCost(troops)));
    // println("sumStrength: " + df.format(healthOptimizer.sumStrength(troops)));
    // println("str per cost: " + (double) healthOptimizer.sumStrength(troops) / (double) healthOptimizer.sumCost(troops));
    // println("----------");

    return troops;
  }

  // private static void println(String s) {
  //   System.out.println(s);
  // }
}
