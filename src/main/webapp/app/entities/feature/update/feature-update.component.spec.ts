jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { FeatureService } from '../service/feature.service';
import { IFeature, Feature } from '../feature.model';

import { FeatureUpdateComponent } from './feature-update.component';

describe('Component Tests', () => {
  describe('Feature Management Update Component', () => {
    let comp: FeatureUpdateComponent;
    let fixture: ComponentFixture<FeatureUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let featureService: FeatureService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [FeatureUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(FeatureUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FeatureUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      featureService = TestBed.inject(FeatureService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const feature: IFeature = { id: 456 };

        activatedRoute.data = of({ feature });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(feature));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<Feature>>();
        const feature = { id: 123 };
        jest.spyOn(featureService, 'update').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ feature });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: feature }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(featureService.update).toHaveBeenCalledWith(feature);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<Feature>>();
        const feature = new Feature();
        jest.spyOn(featureService, 'create').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ feature });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: feature }));
        saveSubject.complete();

        // THEN
        expect(featureService.create).toHaveBeenCalledWith(feature);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<Feature>>();
        const feature = { id: 123 };
        jest.spyOn(featureService, 'update').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ feature });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(featureService.update).toHaveBeenCalledWith(feature);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
