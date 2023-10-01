import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { BonusName } from 'app/entities/enumerations/bonus-name.model';
import { MeasurementUnit } from 'app/entities/enumerations/measurement-unit.model';
import { IBonus, Bonus } from '../bonus.model';

import { BonusService } from './bonus.service';

describe('Bonus Service', () => {
  let service: BonusService;
  let httpMock: HttpTestingController;
  let elemDefault: IBonus;
  let expectedResult: IBonus | IBonus[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(BonusService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 0,
      name: BonusName.STRENGTH,
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

    it('should create a Bonus', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new Bonus()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Bonus', () => {
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

    it('should partial update a Bonus', () => {
      const patchObject = Object.assign(
        {
          name: 'BBBBBB',
        },
        new Bonus()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Bonus', () => {
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

    it('should delete a Bonus', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addBonusToCollectionIfMissing', () => {
      it('should add a Bonus to an empty array', () => {
        const bonus: IBonus = { id: 123 };
        expectedResult = service.addBonusToCollectionIfMissing([], bonus);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(bonus);
      });

      it('should not add a Bonus to an array that contains it', () => {
        const bonus: IBonus = { id: 123 };
        const bonusCollection: IBonus[] = [
          {
            ...bonus,
          },
          { id: 456 },
        ];
        expectedResult = service.addBonusToCollectionIfMissing(bonusCollection, bonus);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Bonus to an array that doesn't contain it", () => {
        const bonus: IBonus = { id: 123 };
        const bonusCollection: IBonus[] = [{ id: 456 }];
        expectedResult = service.addBonusToCollectionIfMissing(bonusCollection, bonus);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(bonus);
      });

      it('should add only unique Bonus to an array', () => {
        const bonusArray: IBonus[] = [{ id: 123 }, { id: 456 }, { id: 39951 }];
        const bonusCollection: IBonus[] = [{ id: 123 }];
        expectedResult = service.addBonusToCollectionIfMissing(bonusCollection, ...bonusArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const bonus: IBonus = { id: 123 };
        const bonus2: IBonus = { id: 456 };
        expectedResult = service.addBonusToCollectionIfMissing([], bonus, bonus2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(bonus);
        expect(expectedResult).toContain(bonus2);
      });

      it('should accept null and undefined values', () => {
        const bonus: IBonus = { id: 123 };
        expectedResult = service.addBonusToCollectionIfMissing([], null, bonus, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(bonus);
      });

      it('should return initial array if no Bonus is added', () => {
        const bonusCollection: IBonus[] = [{ id: 123 }];
        expectedResult = service.addBonusToCollectionIfMissing(bonusCollection, undefined, null);
        expect(expectedResult).toEqual(bonusCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
