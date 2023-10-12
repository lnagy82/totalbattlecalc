import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'unit',
        data: { pageTitle: 'totalbattlecalcApp.unit.home.title' },
        loadChildren: () => import('./unit/unit.module').then(m => m.UnitModule),
      },
      {
        path: 'feature',
        data: { pageTitle: 'totalbattlecalcApp.feature.home.title' },
        loadChildren: () => import('./feature/feature.module').then(m => m.FeatureModule),
      },
      {
        path: 'battle-unit',
        data: { pageTitle: 'totalbattlecalcApp.battleUnit.home.title' },
        loadChildren: () => import('./battle-unit/battle-unit.module').then(m => m.BattleUnitModule),
      },
      {
        path: 'bonus',
        data: { pageTitle: 'totalbattlecalcApp.bonus.home.title' },
        loadChildren: () => import('./bonus/bonus.module').then(m => m.BonusModule),
      },
      {
        path: 'battle',
        data: { pageTitle: 'totalbattlecalcApp.battle.home.title' },
        loadChildren: () => import('./battle/battle.module').then(m => m.BattleModule),
      },
      {
        path: 'troop-calculator',
        data: { pageTitle: 'totalbattlecalcApp.troopCalculator.home.title' },
        loadChildren: () => import('./troop-calculator/troop-calculator.module').then(m => m.TroopCalculatorModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
