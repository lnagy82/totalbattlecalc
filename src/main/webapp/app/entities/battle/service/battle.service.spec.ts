import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as dayjs from 'dayjs';

import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IBattle, Battle } from '../battle.model';

import { BattleService } from './battle.service';

describe('Battle Service', () => {
  let service: BattleService;
  let httpMock: HttpTestingController;
  let elemDefault: IBattle;
  let expectedResult: IBattle | IBattle[] | boolean | null;
  let currentDate: dayjs.Dayjs;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(BattleService);
    httpMock = TestBed.inject(HttpTestingController);
    currentDate = dayjs();

    elemDefault = {
      id: 0,
      date: currentDate,
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign(
        {
          date: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a Battle', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
          date: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          date: currentDate,
        },
        returnedFromService
      );

      service.create(new Battle()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Battle', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          date: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          date: currentDate,
        },
        returnedFromService
      );

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Battle', () => {
      const patchObject = Object.assign(
        {
          date: currentDate.format(DATE_TIME_FORMAT),
        },
        new Battle()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign(
        {
          date: currentDate,
        },
        returnedFromService
      );

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Battle', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          date: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          date: currentDate,
        },
        returnedFromService
      );

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a Battle', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addBattleToCollectionIfMissing', () => {
      it('should add a Battle to an empty array', () => {
        const battle: IBattle = { id: 123 };
        expectedResult = service.addBattleToCollectionIfMissing([], battle);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(battle);
      });

      it('should not add a Battle to an array that contains it', () => {
        const battle: IBattle = { id: 123 };
        const battleCollection: IBattle[] = [
          {
            ...battle,
          },
          { id: 456 },
        ];
        expectedResult = service.addBattleToCollectionIfMissing(battleCollection, battle);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Battle to an array that doesn't contain it", () => {
        const battle: IBattle = { id: 123 };
        const battleCollection: IBattle[] = [{ id: 456 }];
        expectedResult = service.addBattleToCollectionIfMissing(battleCollection, battle);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(battle);
      });

      it('should add only unique Battle to an array', () => {
        const battleArray: IBattle[] = [{ id: 123 }, { id: 456 }, { id: 99144 }];
        const battleCollection: IBattle[] = [{ id: 123 }];
        expectedResult = service.addBattleToCollectionIfMissing(battleCollection, ...battleArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const battle: IBattle = { id: 123 };
        const battle2: IBattle = { id: 456 };
        expectedResult = service.addBattleToCollectionIfMissing([], battle, battle2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(battle);
        expect(expectedResult).toContain(battle2);
      });

      it('should accept null and undefined values', () => {
        const battle: IBattle = { id: 123 };
        expectedResult = service.addBattleToCollectionIfMissing([], null, battle, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(battle);
      });

      it('should return initial array if no Battle is added', () => {
        const battleCollection: IBattle[] = [{ id: 123 }];
        expectedResult = service.addBattleToCollectionIfMissing(battleCollection, undefined, null);
        expect(expectedResult).toEqual(battleCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
