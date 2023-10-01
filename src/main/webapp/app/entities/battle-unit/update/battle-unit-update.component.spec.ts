jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { BattleUnitService } from '../service/battle-unit.service';
import { IBattleUnit, BattleUnit } from '../battle-unit.model';
import { IUnit } from 'app/entities/unit/unit.model';
import { UnitService } from 'app/entities/unit/service/unit.service';
import { IBonus } from 'app/entities/bonus/bonus.model';
import { BonusService } from 'app/entities/bonus/service/bonus.service';

import { BattleUnitUpdateComponent } from './battle-unit-update.component';

describe('Component Tests', () => {
  describe('BattleUnit Management Update Component', () => {
    let comp: BattleUnitUpdateComponent;
    let fixture: ComponentFixture<BattleUnitUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let battleUnitService: BattleUnitService;
    let unitService: UnitService;
    let bonusService: BonusService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [BattleUnitUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(BattleUnitUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BattleUnitUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      battleUnitService = TestBed.inject(BattleUnitService);
      unitService = TestBed.inject(UnitService);
      bonusService = TestBed.inject(BonusService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should call Unit query and add missing value', () => {
        const battleUnit: IBattleUnit = { id: 456 };
        const unit: IUnit = { id: 56369 };
        battleUnit.unit = unit;

        const unitCollection: IUnit[] = [{ id: 69217 }];
        jest.spyOn(unitService, 'query').mockReturnValue(of(new HttpResponse({ body: unitCollection })));
        const additionalUnits = [unit];
        const expectedCollection: IUnit[] = [...additionalUnits, ...unitCollection];
        jest.spyOn(unitService, 'addUnitToCollectionIfMissing').mockReturnValue(expectedCollection);

        activatedRoute.data = of({ battleUnit });
        comp.ngOnInit();

        expect(unitService.query).toHaveBeenCalled();
        expect(unitService.addUnitToCollectionIfMissing).toHaveBeenCalledWith(unitCollection, ...additionalUnits);
        expect(comp.unitsSharedCollection).toEqual(expectedCollection);
      });

      it('Should call Bonus query and add missing value', () => {
        const battleUnit: IBattleUnit = { id: 456 };
        const bonuses: IBonus[] = [{ id: 30509 }];
        battleUnit.bonuses = bonuses;

        const bonusCollection: IBonus[] = [{ id: 73878 }];
        jest.spyOn(bonusService, 'query').mockReturnValue(of(new HttpResponse({ body: bonusCollection })));
        const additionalBonuses = [...bonuses];
        const expectedCollection: IBonus[] = [...additionalBonuses, ...bonusCollection];
        jest.spyOn(bonusService, 'addBonusToCollectionIfMissing').mockReturnValue(expectedCollection);

        activatedRoute.data = of({ battleUnit });
        comp.ngOnInit();

        expect(bonusService.query).toHaveBeenCalled();
        expect(bonusService.addBonusToCollectionIfMissing).toHaveBeenCalledWith(bonusCollection, ...additionalBonuses);
        expect(comp.bonusesSharedCollection).toEqual(expectedCollection);
      });

      it('Should update editForm', () => {
        const battleUnit: IBattleUnit = { id: 456 };
        const unit: IUnit = { id: 38711 };
        battleUnit.unit = unit;
        const bonuses: IBonus = { id: 26476 };
        battleUnit.bonuses = [bonuses];

        activatedRoute.data = of({ battleUnit });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(battleUnit));
        expect(comp.unitsSharedCollection).toContain(unit);
        expect(comp.bonusesSharedCollection).toContain(bonuses);
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<BattleUnit>>();
        const battleUnit = { id: 123 };
        jest.spyOn(battleUnitService, 'update').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ battleUnit });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: battleUnit }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(battleUnitService.update).toHaveBeenCalledWith(battleUnit);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<BattleUnit>>();
        const battleUnit = new BattleUnit();
        jest.spyOn(battleUnitService, 'create').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ battleUnit });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: battleUnit }));
        saveSubject.complete();

        // THEN
        expect(battleUnitService.create).toHaveBeenCalledWith(battleUnit);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<BattleUnit>>();
        const battleUnit = { id: 123 };
        jest.spyOn(battleUnitService, 'update').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ battleUnit });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(battleUnitService.update).toHaveBeenCalledWith(battleUnit);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });

    describe('Tracking relationships identifiers', () => {
      describe('trackUnitById', () => {
        it('Should return tracked Unit primary key', () => {
          const entity = { id: 123 };
          const trackResult = comp.trackUnitById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });

      describe('trackBonusById', () => {
        it('Should return tracked Bonus primary key', () => {
          const entity = { id: 123 };
          const trackResult = comp.trackBonusById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });
    });

    describe('Getting selected relationships', () => {
      describe('getSelectedBonus', () => {
        it('Should return option if no Bonus is selected', () => {
          const option = { id: 123 };
          const result = comp.getSelectedBonus(option);
          expect(result === option).toEqual(true);
        });

        it('Should return selected Bonus for according option', () => {
          const option = { id: 123 };
          const selected = { id: 123 };
          const selected2 = { id: 456 };
          const result = comp.getSelectedBonus(option, [selected2, selected]);
          expect(result === selected).toEqual(true);
          expect(result === selected2).toEqual(false);
          expect(result === option).toEqual(false);
        });

        it('Should return option if this Bonus is not selected', () => {
          const option = { id: 123 };
          const selected = { id: 456 };
          const result = comp.getSelectedBonus(option, [selected]);
          expect(result === option).toEqual(true);
          expect(result === selected).toEqual(false);
        });
      });
    });
  });
});
