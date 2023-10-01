import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IUnit, Unit } from '../unit.model';

import { UnitService } from './unit.service';

describe('Unit Service', () => {
  let service: UnitService;
  let httpMock: HttpTestingController;
  let elemDefault: IUnit;
  let expectedResult: IUnit | IUnit[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(UnitService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 0,
      name: 'AAAAAAA',
      strength: 0,
      health: 0,
      leadership: 0,
      speed: 0,
      initiative: 0,
      foodConsumption: 0,
      carryingCapacity: 0,
      revivalCostAfterAnAttackGold: 0,
      revivalCostAfterDefendingSilver: 0,
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

    it('should create a Unit', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new Unit()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Unit', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          name: 'BBBBBB',
          strength: 1,
          health: 1,
          leadership: 1,
          speed: 1,
          initiative: 1,
          foodConsumption: 1,
          carryingCapacity: 1,
          revivalCostAfterAnAttackGold: 1,
          revivalCostAfterDefendingSilver: 1,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Unit', () => {
      const patchObject = Object.assign(
        {
          strength: 1,
          speed: 1,
          initiative: 1,
          foodConsumption: 1,
          carryingCapacity: 1,
          revivalCostAfterAnAttackGold: 1,
        },
        new Unit()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Unit', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          name: 'BBBBBB',
          strength: 1,
          health: 1,
          leadership: 1,
          speed: 1,
          initiative: 1,
          foodConsumption: 1,
          carryingCapacity: 1,
          revivalCostAfterAnAttackGold: 1,
          revivalCostAfterDefendingSilver: 1,
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

    it('should delete a Unit', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addUnitToCollectionIfMissing', () => {
      it('should add a Unit to an empty array', () => {
        const unit: IUnit = { id: 123 };
        expectedResult = service.addUnitToCollectionIfMissing([], unit);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(unit);
      });

      it('should not add a Unit to an array that contains it', () => {
        const unit: IUnit = { id: 123 };
        const unitCollection: IUnit[] = [
          {
            ...unit,
          },
          { id: 456 },
        ];
        expectedResult = service.addUnitToCollectionIfMissing(unitCollection, unit);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Unit to an array that doesn't contain it", () => {
        const unit: IUnit = { id: 123 };
        const unitCollection: IUnit[] = [{ id: 456 }];
        expectedResult = service.addUnitToCollectionIfMissing(unitCollection, unit);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(unit);
      });

      it('should add only unique Unit to an array', () => {
        const unitArray: IUnit[] = [{ id: 123 }, { id: 456 }, { id: 93989 }];
        const unitCollection: IUnit[] = [{ id: 123 }];
        expectedResult = service.addUnitToCollectionIfMissing(unitCollection, ...unitArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const unit: IUnit = { id: 123 };
        const unit2: IUnit = { id: 456 };
        expectedResult = service.addUnitToCollectionIfMissing([], unit, unit2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(unit);
        expect(expectedResult).toContain(unit2);
      });

      it('should accept null and undefined values', () => {
        const unit: IUnit = { id: 123 };
        expectedResult = service.addUnitToCollectionIfMissing([], null, unit, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(unit);
      });

      it('should return initial array if no Unit is added', () => {
        const unitCollection: IUnit[] = [{ id: 123 }];
        expectedResult = service.addUnitToCollectionIfMissing(unitCollection, undefined, null);
        expect(expectedResult).toEqual(unitCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
