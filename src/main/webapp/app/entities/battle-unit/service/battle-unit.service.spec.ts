import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IBattleUnit, BattleUnit } from '../battle-unit.model';

import { BattleUnitService } from './battle-unit.service';

describe('BattleUnit Service', () => {
  let service: BattleUnitService;
  let httpMock: HttpTestingController;
  let elemDefault: IBattleUnit;
  let expectedResult: IBattleUnit | IBattleUnit[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(BattleUnitService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 0,
      number: 0,
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

    it('should create a BattleUnit', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new BattleUnit()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a BattleUnit', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          number: 1,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a BattleUnit', () => {
      const patchObject = Object.assign({}, new BattleUnit());

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of BattleUnit', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          number: 1,
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

    it('should delete a BattleUnit', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addBattleUnitToCollectionIfMissing', () => {
      it('should add a BattleUnit to an empty array', () => {
        const battleUnit: IBattleUnit = { id: 123 };
        expectedResult = service.addBattleUnitToCollectionIfMissing([], battleUnit);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(battleUnit);
      });

      it('should not add a BattleUnit to an array that contains it', () => {
        const battleUnit: IBattleUnit = { id: 123 };
        const battleUnitCollection: IBattleUnit[] = [
          {
            ...battleUnit,
          },
          { id: 456 },
        ];
        expectedResult = service.addBattleUnitToCollectionIfMissing(battleUnitCollection, battleUnit);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a BattleUnit to an array that doesn't contain it", () => {
        const battleUnit: IBattleUnit = { id: 123 };
        const battleUnitCollection: IBattleUnit[] = [{ id: 456 }];
        expectedResult = service.addBattleUnitToCollectionIfMissing(battleUnitCollection, battleUnit);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(battleUnit);
      });

      it('should add only unique BattleUnit to an array', () => {
        const battleUnitArray: IBattleUnit[] = [{ id: 123 }, { id: 456 }, { id: 57103 }];
        const battleUnitCollection: IBattleUnit[] = [{ id: 123 }];
        expectedResult = service.addBattleUnitToCollectionIfMissing(battleUnitCollection, ...battleUnitArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const battleUnit: IBattleUnit = { id: 123 };
        const battleUnit2: IBattleUnit = { id: 456 };
        expectedResult = service.addBattleUnitToCollectionIfMissing([], battleUnit, battleUnit2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(battleUnit);
        expect(expectedResult).toContain(battleUnit2);
      });

      it('should accept null and undefined values', () => {
        const battleUnit: IBattleUnit = { id: 123 };
        expectedResult = service.addBattleUnitToCollectionIfMissing([], null, battleUnit, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(battleUnit);
      });

      it('should return initial array if no BattleUnit is added', () => {
        const battleUnitCollection: IBattleUnit[] = [{ id: 123 }];
        expectedResult = service.addBattleUnitToCollectionIfMissing(battleUnitCollection, undefined, null);
        expect(expectedResult).toEqual(battleUnitCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
