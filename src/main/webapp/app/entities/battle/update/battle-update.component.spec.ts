jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { BattleService } from '../service/battle.service';
import { IBattle, Battle } from '../battle.model';

import { BattleUpdateComponent } from './battle-update.component';

describe('Component Tests', () => {
  describe('Battle Management Update Component', () => {
    let comp: BattleUpdateComponent;
    let fixture: ComponentFixture<BattleUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let battleService: BattleService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [BattleUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(BattleUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BattleUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      battleService = TestBed.inject(BattleService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const battle: IBattle = { id: 456 };

        activatedRoute.data = of({ battle });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(battle));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<Battle>>();
        const battle = { id: 123 };
        jest.spyOn(battleService, 'update').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ battle });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: battle }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(battleService.update).toHaveBeenCalledWith(battle);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<Battle>>();
        const battle = new Battle();
        jest.spyOn(battleService, 'create').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ battle });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: battle }));
        saveSubject.complete();

        // THEN
        expect(battleService.create).toHaveBeenCalledWith(battle);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<Battle>>();
        const battle = { id: 123 };
        jest.spyOn(battleService, 'update').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ battle });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(battleService.update).toHaveBeenCalledWith(battle);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
