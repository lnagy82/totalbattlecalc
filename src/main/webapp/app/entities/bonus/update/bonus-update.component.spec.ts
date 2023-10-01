jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { BonusService } from '../service/bonus.service';
import { IBonus, Bonus } from '../bonus.model';

import { BonusUpdateComponent } from './bonus-update.component';

describe('Component Tests', () => {
  describe('Bonus Management Update Component', () => {
    let comp: BonusUpdateComponent;
    let fixture: ComponentFixture<BonusUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let bonusService: BonusService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [BonusUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(BonusUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BonusUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      bonusService = TestBed.inject(BonusService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const bonus: IBonus = { id: 456 };

        activatedRoute.data = of({ bonus });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(bonus));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<Bonus>>();
        const bonus = { id: 123 };
        jest.spyOn(bonusService, 'update').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ bonus });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: bonus }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(bonusService.update).toHaveBeenCalledWith(bonus);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<Bonus>>();
        const bonus = new Bonus();
        jest.spyOn(bonusService, 'create').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ bonus });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: bonus }));
        saveSubject.complete();

        // THEN
        expect(bonusService.create).toHaveBeenCalledWith(bonus);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<Bonus>>();
        const bonus = { id: 123 };
        jest.spyOn(bonusService, 'update').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ bonus });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(bonusService.update).toHaveBeenCalledWith(bonus);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
