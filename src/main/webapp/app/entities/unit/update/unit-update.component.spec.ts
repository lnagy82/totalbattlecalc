jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { UnitService } from '../service/unit.service';
import { IUnit, Unit } from '../unit.model';
import { IFeature } from 'app/entities/feature/feature.model';
import { FeatureService } from 'app/entities/feature/service/feature.service';

import { UnitUpdateComponent } from './unit-update.component';

describe('Component Tests', () => {
  describe('Unit Management Update Component', () => {
    let comp: UnitUpdateComponent;
    let fixture: ComponentFixture<UnitUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let unitService: UnitService;
    let featureService: FeatureService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [UnitUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(UnitUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UnitUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      unitService = TestBed.inject(UnitService);
      featureService = TestBed.inject(FeatureService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should call Feature query and add missing value', () => {
        const unit: IUnit = { id: 456 };
        const features: IFeature[] = [{ id: 93936 }];
        unit.features = features;

        const featureCollection: IFeature[] = [{ id: 18135 }];
        jest.spyOn(featureService, 'query').mockReturnValue(of(new HttpResponse({ body: featureCollection })));
        const additionalFeatures = [...features];
        const expectedCollection: IFeature[] = [...additionalFeatures, ...featureCollection];
        jest.spyOn(featureService, 'addFeatureToCollectionIfMissing').mockReturnValue(expectedCollection);

        activatedRoute.data = of({ unit });
        comp.ngOnInit();

        expect(featureService.query).toHaveBeenCalled();
        expect(featureService.addFeatureToCollectionIfMissing).toHaveBeenCalledWith(featureCollection, ...additionalFeatures);
        expect(comp.featuresSharedCollection).toEqual(expectedCollection);
      });

      it('Should update editForm', () => {
        const unit: IUnit = { id: 456 };
        const features: IFeature = { id: 56283 };
        unit.features = [features];

        activatedRoute.data = of({ unit });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(unit));
        expect(comp.featuresSharedCollection).toContain(features);
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<Unit>>();
        const unit = { id: 123 };
        jest.spyOn(unitService, 'update').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ unit });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: unit }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(unitService.update).toHaveBeenCalledWith(unit);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<Unit>>();
        const unit = new Unit();
        jest.spyOn(unitService, 'create').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ unit });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: unit }));
        saveSubject.complete();

        // THEN
        expect(unitService.create).toHaveBeenCalledWith(unit);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<Unit>>();
        const unit = { id: 123 };
        jest.spyOn(unitService, 'update').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ unit });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(unitService.update).toHaveBeenCalledWith(unit);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });

    describe('Tracking relationships identifiers', () => {
      describe('trackFeatureById', () => {
        it('Should return tracked Feature primary key', () => {
          const entity = { id: 123 };
          const trackResult = comp.trackFeatureById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });
    });

    describe('Getting selected relationships', () => {
      describe('getSelectedFeature', () => {
        it('Should return option if no Feature is selected', () => {
          const option = { id: 123 };
          const result = comp.getSelectedFeature(option);
          expect(result === option).toEqual(true);
        });

        it('Should return selected Feature for according option', () => {
          const option = { id: 123 };
          const selected = { id: 123 };
          const selected2 = { id: 456 };
          const result = comp.getSelectedFeature(option, [selected2, selected]);
          expect(result === selected).toEqual(true);
          expect(result === selected2).toEqual(false);
          expect(result === option).toEqual(false);
        });

        it('Should return option if this Feature is not selected', () => {
          const option = { id: 123 };
          const selected = { id: 456 };
          const result = comp.getSelectedFeature(option, [selected]);
          expect(result === option).toEqual(true);
          expect(result === selected).toEqual(false);
        });
      });
    });
  });
});
