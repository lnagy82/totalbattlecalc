jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { IBattle, Battle } from '../battle.model';
import { BattleService } from '../service/battle.service';

import { BattleRoutingResolveService } from './battle-routing-resolve.service';

describe('Battle routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: BattleRoutingResolveService;
  let service: BattleService;
  let resultBattle: IBattle | undefined;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [Router, ActivatedRouteSnapshot],
    });
    mockRouter = TestBed.inject(Router);
    mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
    routingResolveService = TestBed.inject(BattleRoutingResolveService);
    service = TestBed.inject(BattleService);
    resultBattle = undefined;
  });

  describe('resolve', () => {
    it('should return IBattle returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultBattle = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultBattle).toEqual({ id: 123 });
    });

    it('should return new IBattle if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultBattle = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultBattle).toEqual(new Battle());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as Battle })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultBattle = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultBattle).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
