import { HTTP_INTERCEPTORS, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { delay } from 'rxjs/operators';

@Injectable()
export class FakeBackendInterceptor implements HttpInterceptor {
  public intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const { url, method, headers, body } = request;

    return handleRoute();

    function handleRoute(): Observable<HttpEvent<any>> {
      switch (true) {
        case url.endsWith('/users/authenticate') && method === 'POST':
          return authenticate();
        case url.endsWith('/users/register') && method === 'POST':
          return register();
        case url.endsWith('/users') && method === 'GET':
          return getUsers();
        case url.endsWith('units') && method === 'GET':
          return getUnits();
        case url.endsWith('api/account') && method === 'GET':
          return getApiAccount();
        case url.endsWith('management/info') && method === 'GET':
          return getManagementInfo();
        case /\/users\/\d+$/.exec(url) && method === 'GET':
          return getUserById();
        case /\/users\/\d+$/.exec(url) && method === 'PUT':
          return updateUser();
        case /\/users\/\d+$/.exec(url) && method === 'DELETE':
          return deleteUser();
        default:
          // pass through any requests not handled above
          return next.handle(request);
      }
    }
  }
}
function authenticate(): Observable<HttpEvent<any>> {
  throw new Error('Function not implemented.');
}

function register(): Observable<HttpEvent<any>> {
  throw new Error('Function not implemented.');
}

function getUsers(): Observable<HttpEvent<any>> {
  throw new Error('Function not implemented.');
}

function getUserById(): Observable<HttpEvent<any>> {
  throw new Error('Function not implemented.');
}

function updateUser(): Observable<HttpEvent<any>> {
  throw new Error('Function not implemented.');
}

function deleteUser(): Observable<HttpEvent<any>> {
  throw new Error('Function not implemented.');
}

function ok(body?: any): Observable<HttpEvent<any>> {
  return of(new HttpResponse({ status: 200, body }))
      .pipe(delay(500)); // delay observable to simulate server api call
}

export const fakeBackendProvider = {
  // use fake backend in place of Http service for backend-less development
  provide: HTTP_INTERCEPTORS,
  useClass: FakeBackendInterceptor,
  multi: true,
};
function getUnits(): Observable<HttpEvent<any>> {
  return ok();
}
function getManagementInfo(): Observable<HttpEvent<any>> {
  return ok({
    activeProfiles: ['prod']
  });
}

function getApiAccount(): Observable<HttpEvent<any>> {
  const user = {};
  return ok({
      ...basicDetails(user),
      token: 'fake-jwt-token'
  })
}

function basicDetails(user: any): any {
  const { id, login, firstName, lastName } = user;
  return { id, login: 'user' , firstName:'Default', lastName:'User', langKey:'en', email: 'email@email.com', authorities: ['USER', 'ADMIN'] };
}

