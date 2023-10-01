import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { FeatureName } from 'app/entities/enumerations/feature-name.model';
import { MeasurementUnit } from 'app/entities/enumerations/measurement-unit.model';
import { IFeature, Feature } from '../feature.model';

import { FeatureService } from './feature.service';

describe('Feature Service', () => {
  let service: FeatureService;
  let httpMock: HttpTestingController;
  let elemDefault: IFeature;
  let expectedResult: IFeature | IFeature[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(FeatureService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 0,
      name: FeatureName.HUMAN,
      value: 0,
      unit: MeasurementUnit.NONE,
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign({}, elemDefault);

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a Feature', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new Feature()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Feature', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          name: 'BBBBBB',
          value: 1,
          unit: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Feature', () => {
      const patchObject = Object.assign(
        {
          name: 'BBBBBB',
          value: 1,
          unit: 'BBBBBB',
        },
        new Feature()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Feature', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          name: 'BBBBBB',
          value: 1,
          unit: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a Feature', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addFeatureToCollectionIfMissing', () => {
      it('should add a Feature to an empty array', () => {
        const feature: IFeature = { id: 123 };
        expectedResult = service.addFeatureToCollectionIfMissing([], feature);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(feature);
      });

      it('should not add a Feature to an array that contains it', () => {
        const feature: IFeature = { id: 123 };
        const featureCollection: IFeature[] = [
          {
            ...feature,
          },
          { id: 456 },
        ];
        expectedResult = service.addFeatureToCollectionIfMissing(featureCollection, feature);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Feature to an array that doesn't contain it", () => {
        const feature: IFeature = { id: 123 };
        const featureCollection: IFeature[] = [{ id: 456 }];
        expectedResult = service.addFeatureToCollectionIfMissing(featureCollection, feature);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(feature);
      });

      it('should add only unique Feature to an array', () => {
        const featureArray: IFeature[] = [{ id: 123 }, { id: 456 }, { id: 53264 }];
        const featureCollection: IFeature[] = [{ id: 123 }];
        expectedResult = service.addFeatureToCollectionIfMissing(featureCollection, ...featureArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const feature: IFeature = { id: 123 };
        const feature2: IFeature = { id: 456 };
        expectedResult = service.addFeatureToCollectionIfMissing([], feature, feature2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(feature);
        expect(expectedResult).toContain(feature2);
      });

      it('should accept null and undefined values', () => {
        const feature: IFeature = { id: 123 };
        expectedResult = service.addFeatureToCollectionIfMissing([], null, feature, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(feature);
      });

      it('should return initial array if no Feature is added', () => {
        const featureCollection: IFeature[] = [{ id: 123 }];
        expectedResult = service.addFeatureToCollectionIfMissing(featureCollection, undefined, null);
        expect(expectedResult).toEqual(featureCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
