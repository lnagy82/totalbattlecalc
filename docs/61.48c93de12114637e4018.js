"use strict";(self.webpackChunktotalbattlecalc=self.webpackChunktotalbattlecalc||[]).push([[61],{5061:(mt,y,l)=>{l.r(y),l.d(y,{BattleUnitModule:()=>gt});var j=l(1382),s=l(805),g=l(9375),J=l(6905),d=l(4991),t=l(2741),N=l(7575),D=l(7354);class B{constructor(o,n,i,a){this.id=o,this.number=n,this.unit=i,this.bonuses=a}}function m(e){return e.id}var I=l(1887),L=l(8986);let b=(()=>{class e{constructor(n,i){this.http=n,this.applicationConfigService=i,this.resourceUrl=this.applicationConfigService.getEndpointFor("api/battle-units")}create(n){return this.http.post(this.resourceUrl,n,{observe:"response"})}update(n){return this.http.put(`${this.resourceUrl}/${m(n)}`,n,{observe:"response"})}partialUpdate(n){return this.http.patch(`${this.resourceUrl}/${m(n)}`,n,{observe:"response"})}find(n){return this.http.get(`${this.resourceUrl}/${n}`,{observe:"response"})}query(n){const i=(0,D.b)(n);return this.http.get(this.resourceUrl,{params:i,observe:"response"})}delete(n){return this.http.delete(`${this.resourceUrl}/${n}`,{observe:"response"})}addBattleUnitToCollectionIfMissing(n,...i){const a=i.filter(N.E);if(a.length>0){const r=n.map(p=>m(p));return[...a.filter(p=>{const _=m(p);return null!=_&&!r.includes(_)&&(r.push(_),!0)}),...n]}return n}}return e.\u0275fac=function(n){return new(n||e)(t.LFG(I.eN),t.LFG(L.y))},e.\u0275prov=t.Yz7({token:e,factory:e.\u0275fac,providedIn:"root"}),e})();var T=l(2444),U=l(6274),u=l(3324),h=l(4243),Z=l(6662),v=l(4477);const P=function(e){return{id:e}};function Q(e,o){if(1&e){const n=t.EpF();t.TgZ(0,"form",1),t.NdJ("ngSubmit",function(){t.CHM(n);const a=t.oxw();return a.confirmDelete(a.battleUnit.id)}),t._uU(1,"\n  "),t.TgZ(2,"div",2),t._uU(3,"\n    "),t.TgZ(4,"h4",3),t._uU(5,"Confirm delete operation"),t.qZA(),t._uU(6,"\n\n    "),t.TgZ(7,"button",4),t.NdJ("click",function(){return t.CHM(n),t.oxw().cancel()}),t._uU(8,"\xd7"),t.qZA(),t._uU(9,"\n  "),t.qZA(),t._uU(10,"\n\n  "),t.TgZ(11,"div",5),t._uU(12,"\n    "),t._UZ(13,"jhi-alert-error"),t._uU(14,"\n\n    "),t.TgZ(15,"p",6),t._uU(16,"\n      Are you sure you want to delete this Battle Unit?\n    "),t.qZA(),t._uU(17,"\n  "),t.qZA(),t._uU(18,"\n\n  "),t.TgZ(19,"div",7),t._uU(20,"\n    "),t.TgZ(21,"button",8),t.NdJ("click",function(){return t.CHM(n),t.oxw().cancel()}),t._uU(22,"\n      "),t._UZ(23,"fa-icon",9),t._uU(24,"\xa0"),t.TgZ(25,"span",10),t._uU(26,"Cancel"),t.qZA(),t._uU(27,"\n    "),t.qZA(),t._uU(28,"\n\n    "),t.TgZ(29,"button",11),t._uU(30,"\n      "),t._UZ(31,"fa-icon",12),t._uU(32,"\xa0"),t.TgZ(33,"span",13),t._uU(34,"Delete"),t.qZA(),t._uU(35,"\n    "),t.qZA(),t._uU(36,"\n  "),t.qZA(),t._uU(37,"\n"),t.qZA()}if(2&e){const n=t.oxw();t.xp6(15),t.Q6J("translateValues",t.VKq(1,P,n.battleUnit.id))}}let k=(()=>{class e{constructor(n,i){this.battleUnitService=n,this.activeModal=i}cancel(){this.activeModal.dismiss()}confirmDelete(n){this.battleUnitService.delete(n).subscribe(()=>{this.activeModal.close("deleted")})}}return e.\u0275fac=function(n){return new(n||e)(t.Y36(b),t.Y36(T.Kz))},e.\u0275cmp=t.Xpm({type:e,selectors:[["ng-component"]],decls:2,vars:1,consts:[["name","deleteForm",3,"ngSubmit",4,"ngIf"],["name","deleteForm",3,"ngSubmit"],[1,"modal-header"],["data-cy","battleUnitDeleteDialogHeading","jhiTranslate","entity.delete.title",1,"modal-title"],["type","button","data-dismiss","modal","aria-hidden","true",1,"close",3,"click"],[1,"modal-body"],["id","jhi-delete-battleUnit-heading","jhiTranslate","totalbattlecalcApp.battleUnit.delete.question",3,"translateValues"],[1,"modal-footer"],["type","button","data-dismiss","modal",1,"btn","btn-secondary",3,"click"],["icon","ban"],["jhiTranslate","entity.action.cancel"],["id","jhi-confirm-delete-battleUnit","data-cy","entityConfirmDeleteButton","type","submit",1,"btn","btn-danger"],["icon","times"],["jhiTranslate","entity.action.delete"]],template:function(n,i){1&n&&(t.YNc(0,Q,38,3,"form",0),t._uU(1,"\n")),2&n&&t.Q6J("ngIf",i.battleUnit)},directives:[U.O5,u._Y,u.JL,u.F,h.P,Z.A,v.BN],encapsulation:2}),e})();var C=l(1066),w=l(6059),M=l(9044),Y=l(4150);function O(e,o){1&e&&(t.TgZ(0,"div",12),t._uU(1,"\n    "),t.TgZ(2,"span",13),t._uU(3,"No battleUnits found"),t.qZA(),t._uU(4,"\n  "),t.qZA())}const R=function(e){return["/unit",e,"view"]};function z(e,o){if(1&e&&(t.TgZ(0,"div"),t._uU(1,"\n              "),t.TgZ(2,"a",27),t._uU(3),t.qZA(),t._uU(4,"\n            "),t.qZA()),2&e){const n=t.oxw().$implicit;t.xp6(2),t.Q6J("routerLink",t.VKq(2,R,null==n.unit?null:n.unit.id)),t.xp6(1),t.Oqu(null==n.unit?null:n.unit.id)}}const q=function(e){return["/battle-unit",e,"view"]},E=function(e){return["/battle-unit",e,"edit"]};function V(e,o){if(1&e){const n=t.EpF();t.TgZ(0,"tr",26),t._uU(1,"\n          "),t.TgZ(2,"td"),t._uU(3,"\n            "),t.TgZ(4,"a",27),t._uU(5),t.qZA(),t._uU(6,"\n          "),t.qZA(),t._uU(7,"\n          "),t.TgZ(8,"td"),t._uU(9),t.qZA(),t._uU(10,"\n          "),t.TgZ(11,"td"),t._uU(12,"\n            "),t.YNc(13,z,5,4,"div",11),t._uU(14,"\n          "),t.qZA(),t._uU(15,"\n          "),t.TgZ(16,"td",28),t._uU(17,"\n            "),t.TgZ(18,"div",29),t._uU(19,"\n              "),t.TgZ(20,"button",30),t._uU(21,"\n                "),t._UZ(22,"fa-icon",31),t._uU(23,"\n                "),t.TgZ(24,"span",32),t._uU(25,"View"),t.qZA(),t._uU(26,"\n              "),t.qZA(),t._uU(27,"\n\n              "),t.TgZ(28,"button",33),t._uU(29,"\n                "),t._UZ(30,"fa-icon",34),t._uU(31,"\n                "),t.TgZ(32,"span",35),t._uU(33,"Edit"),t.qZA(),t._uU(34,"\n              "),t.qZA(),t._uU(35,"\n\n              "),t.TgZ(36,"button",36),t.NdJ("click",function(){const r=t.CHM(n).$implicit;return t.oxw(2).delete(r)}),t._uU(37,"\n                "),t._UZ(38,"fa-icon",37),t._uU(39,"\n                "),t.TgZ(40,"span",38),t._uU(41,"Delete"),t.qZA(),t._uU(42,"\n              "),t.qZA(),t._uU(43,"\n            "),t.qZA(),t._uU(44,"\n          "),t.qZA(),t._uU(45,"\n        "),t.qZA()}if(2&e){const n=o.$implicit;t.xp6(4),t.Q6J("routerLink",t.VKq(6,q,n.id)),t.xp6(1),t.Oqu(n.id),t.xp6(4),t.Oqu(n.number),t.xp6(4),t.Q6J("ngIf",n.unit),t.xp6(7),t.Q6J("routerLink",t.VKq(8,q,n.id)),t.xp6(8),t.Q6J("routerLink",t.VKq(10,E,n.id))}}function H(e,o){if(1&e){const n=t.EpF();t.TgZ(0,"div",14),t._uU(1,"\n    "),t.TgZ(2,"table",15),t._uU(3,"\n      "),t.TgZ(4,"thead"),t._uU(5,"\n        "),t.TgZ(6,"tr",16),t.NdJ("predicateChange",function(a){return t.CHM(n),t.oxw().predicate=a})("ascendingChange",function(a){return t.CHM(n),t.oxw().ascending=a})("sortChange",function(){return t.CHM(n),t.oxw().loadPage()}),t._uU(7,"\n          "),t.TgZ(8,"th",17),t.TgZ(9,"span",18),t._uU(10,"ID"),t.qZA(),t._uU(11," "),t._UZ(12,"fa-icon",19),t.qZA(),t._uU(13,"\n          "),t.TgZ(14,"th",20),t._uU(15,"\n            "),t.TgZ(16,"span",21),t._uU(17,"Number"),t.qZA(),t._uU(18," "),t._UZ(19,"fa-icon",19),t._uU(20,"\n          "),t.qZA(),t._uU(21,"\n          "),t.TgZ(22,"th",22),t._uU(23,"\n            "),t.TgZ(24,"span",23),t._uU(25,"Unit"),t.qZA(),t._uU(26," "),t._UZ(27,"fa-icon",19),t._uU(28,"\n          "),t.qZA(),t._uU(29,"\n          "),t._UZ(30,"th",24),t._uU(31,"\n        "),t.qZA(),t._uU(32,"\n      "),t.qZA(),t._uU(33,"\n      "),t.TgZ(34,"tbody"),t._uU(35,"\n        "),t.YNc(36,V,46,12,"tr",25),t._uU(37,"\n      "),t.qZA(),t._uU(38,"\n    "),t.qZA(),t._uU(39,"\n  "),t.qZA()}if(2&e){const n=t.oxw();t.xp6(6),t.Q6J("predicate",n.predicate)("ascending",n.ascending),t.xp6(30),t.Q6J("ngForOf",n.battleUnits)("ngForTrackBy",n.trackId)}}const $=function(e,o,n){return{page:e,totalItems:o,itemsPerPage:n}};function K(e,o){if(1&e){const n=t.EpF();t.TgZ(0,"div"),t._uU(1,"\n    "),t.TgZ(2,"div",39),t._uU(3,"\n      "),t._UZ(4,"jhi-item-count",40),t._uU(5,"\n    "),t.qZA(),t._uU(6,"\n\n    "),t.TgZ(7,"div",39),t._uU(8,"\n      "),t.TgZ(9,"ngb-pagination",41),t.NdJ("pageChange",function(a){return t.CHM(n),t.oxw().ngbPaginationPage=a})("pageChange",function(a){return t.CHM(n),t.oxw().loadPage(a)}),t.qZA(),t._uU(10,"\n    "),t.qZA(),t._uU(11,"\n  "),t.qZA()}if(2&e){const n=t.oxw();t.xp6(4),t.Q6J("params",t.kEZ(7,$,n.page,n.totalItems,n.itemsPerPage)),t.xp6(5),t.Q6J("collectionSize",n.totalItems)("page",n.ngbPaginationPage)("pageSize",n.itemsPerPage)("maxSize",5)("rotate",!0)("boundaryLinks",!0)}}const G=function(){return["/battle-unit/new"]};let X=(()=>{class e{constructor(n,i,a,r){this.battleUnitService=n,this.activatedRoute=i,this.router=a,this.modalService=r,this.isLoading=!1,this.totalItems=0,this.itemsPerPage=d.gK,this.ngbPaginationPage=1}loadPage(n,i){var a;this.isLoading=!0;const r=null!==(a=null!=n?n:this.page)&&void 0!==a?a:1;this.battleUnitService.query({page:r-1,size:this.itemsPerPage,sort:this.sort()}).subscribe(c=>{this.isLoading=!1,this.onSuccess(c.body,c.headers,r,!i)},()=>{this.isLoading=!1,this.onError()})}ngOnInit(){this.handleNavigation()}trackId(n,i){return i.id}delete(n){const i=this.modalService.open(k,{size:"lg",backdrop:"static"});i.componentInstance.battleUnit=n,i.closed.subscribe(a=>{"deleted"===a&&this.loadPage()})}sort(){const n=[this.predicate+","+(this.ascending?d.aW:d.jo)];return"id"!==this.predicate&&n.push("id"),n}handleNavigation(){(0,J.aj)([this.activatedRoute.data,this.activatedRoute.queryParamMap]).subscribe(([n,i])=>{var a;const r=i.get("page"),c=+(null!=r?r:1),p=(null!==(a=i.get(d._l))&&void 0!==a?a:n.defaultSort).split(","),_=p[0],F=p[1]===d.aW;(c!==this.page||_!==this.predicate||F!==this.ascending)&&(this.predicate=_,this.ascending=F,this.loadPage(c,!0))})}onSuccess(n,i,a,r){this.totalItems=Number(i.get("X-Total-Count")),this.page=a,r&&this.router.navigate(["/battle-unit"],{queryParams:{page:this.page,size:this.itemsPerPage,sort:this.predicate+","+(this.ascending?d.aW:d.jo)}}),this.battleUnits=null!=n?n:[],this.ngbPaginationPage=this.page}onError(){var n;this.ngbPaginationPage=null!==(n=this.page)&&void 0!==n?n:1}}return e.\u0275fac=function(n){return new(n||e)(t.Y36(b),t.Y36(s.gz),t.Y36(s.F0),t.Y36(T.FF))},e.\u0275cmp=t.Xpm({type:e,selectors:[["jhi-battle-unit"]],decls:38,vars:7,consts:[["id","page-heading","data-cy","BattleUnitHeading"],["jhiTranslate","totalbattlecalcApp.battleUnit.home.title"],[1,"d-flex","justify-content-end"],[1,"btn","btn-info","mr-2",3,"disabled","click"],["icon","sync",3,"spin"],["jhiTranslate","totalbattlecalcApp.battleUnit.home.refreshListLabel"],["id","jh-create-entity","data-cy","entityCreateButton",1,"btn","btn-primary","jh-create-entity","create-battle-unit",3,"routerLink"],["icon","plus"],["jhiTranslate","totalbattlecalcApp.battleUnit.home.createLabel"],["class","alert alert-warning","id","no-result",4,"ngIf"],["class","table-responsive","id","entities",4,"ngIf"],[4,"ngIf"],["id","no-result",1,"alert","alert-warning"],["jhiTranslate","totalbattlecalcApp.battleUnit.home.notFound"],["id","entities",1,"table-responsive"],["aria-describedby","page-heading",1,"table","table-striped"],["jhiSort","",3,"predicate","ascending","predicateChange","ascendingChange","sortChange"],["scope","col","jhiSortBy","id"],["jhiTranslate","global.field.id"],["icon","sort"],["scope","col","jhiSortBy","number"],["jhiTranslate","totalbattlecalcApp.battleUnit.number"],["scope","col","jhiSortBy","unit.id"],["jhiTranslate","totalbattlecalcApp.battleUnit.unit"],["scope","col"],["data-cy","entityTable",4,"ngFor","ngForOf","ngForTrackBy"],["data-cy","entityTable"],[3,"routerLink"],[1,"text-right"],[1,"btn-group"],["type","submit","data-cy","entityDetailsButton",1,"btn","btn-info","btn-sm",3,"routerLink"],["icon","eye"],["jhiTranslate","entity.action.view",1,"d-none","d-md-inline"],["type","submit","data-cy","entityEditButton",1,"btn","btn-primary","btn-sm",3,"routerLink"],["icon","pencil-alt"],["jhiTranslate","entity.action.edit",1,"d-none","d-md-inline"],["type","submit","data-cy","entityDeleteButton",1,"btn","btn-danger","btn-sm",3,"click"],["icon","times"],["jhiTranslate","entity.action.delete",1,"d-none","d-md-inline"],[1,"row","justify-content-center"],[3,"params"],[3,"collectionSize","page","pageSize","maxSize","rotate","boundaryLinks","pageChange"]],template:function(n,i){1&n&&(t.TgZ(0,"div"),t._uU(1,"\n  "),t.TgZ(2,"h2",0),t._uU(3,"\n    "),t.TgZ(4,"span",1),t._uU(5,"Battle Units"),t.qZA(),t._uU(6,"\n\n    "),t.TgZ(7,"div",2),t._uU(8,"\n      "),t.TgZ(9,"button",3),t.NdJ("click",function(){return i.loadPage()}),t._uU(10,"\n        "),t._UZ(11,"fa-icon",4),t._uU(12,"\n        "),t.TgZ(13,"span",5),t._uU(14,"Refresh List"),t.qZA(),t._uU(15,"\n      "),t.qZA(),t._uU(16,"\n\n      "),t.TgZ(17,"button",6),t._uU(18,"\n        "),t._UZ(19,"fa-icon",7),t._uU(20,"\n        "),t.TgZ(21,"span",8),t._uU(22," Create a new Battle Unit "),t.qZA(),t._uU(23,"\n      "),t.qZA(),t._uU(24,"\n    "),t.qZA(),t._uU(25,"\n  "),t.qZA(),t._uU(26,"\n\n  "),t._UZ(27,"jhi-alert-error"),t._uU(28,"\n\n  "),t._UZ(29,"jhi-alert"),t._uU(30,"\n\n  "),t.YNc(31,O,5,0,"div",9),t._uU(32,"\n\n  "),t.YNc(33,H,40,4,"div",10),t._uU(34,"\n\n  "),t.YNc(35,K,12,11,"div",11),t._uU(36,"\n"),t.qZA(),t._uU(37,"\n")),2&n&&(t.xp6(9),t.Q6J("disabled",i.isLoading),t.xp6(2),t.Q6J("spin",i.isLoading),t.xp6(6),t.Q6J("routerLink",t.DdM(6,G)),t.xp6(14),t.Q6J("ngIf",0===(null==i.battleUnits?null:i.battleUnits.length)),t.xp6(2),t.Q6J("ngIf",i.battleUnits&&i.battleUnits.length>0),t.xp6(2),t.Q6J("ngIf",i.battleUnits&&i.battleUnits.length>0))},directives:[h.P,v.BN,s.rH,Z.A,C.w,U.O5,w.b,M.T,U.sg,s.yS,Y.N,T.N9],encapsulation:2}),e})();const W=function(e){return["/unit",e,"view"]};function tt(e,o){if(1&e&&(t.TgZ(0,"div"),t._uU(1,"\n            "),t.TgZ(2,"a",17),t._uU(3),t.qZA(),t._uU(4,"\n          "),t.qZA()),2&e){const n=t.oxw(2);t.xp6(2),t.Q6J("routerLink",t.VKq(2,W,null==n.battleUnit.unit?null:n.battleUnit.unit.id)),t.xp6(1),t.Oqu(null==n.battleUnit.unit?null:n.battleUnit.unit.id)}}const nt=function(e){return["/bonus",e,"view"]};function et(e,o){if(1&e&&(t.TgZ(0,"span"),t._uU(1,"\n            "),t.TgZ(2,"a",17),t._uU(3),t.qZA(),t._uU(4),t.qZA()),2&e){const n=o.$implicit,i=o.last;t.xp6(2),t.Q6J("routerLink",t.VKq(3,nt,null==n?null:n.id)),t.xp6(1),t.Oqu(n.id),t.xp6(1),t.hij("",i?"":", ","\n          ")}}const it=function(e){return["/battle-unit",e,"edit"]};function at(e,o){if(1&e){const n=t.EpF();t.TgZ(0,"div"),t._uU(1,"\n      "),t.TgZ(2,"h2",3),t.TgZ(3,"span",4),t._uU(4,"Battle Unit"),t.qZA(),t.qZA(),t._uU(5,"\n\n      "),t._UZ(6,"hr"),t._uU(7,"\n\n      "),t._UZ(8,"jhi-alert-error"),t._uU(9,"\n\n      "),t._UZ(10,"jhi-alert"),t._uU(11,"\n\n      "),t.TgZ(12,"dl",5),t._uU(13,"\n        "),t.TgZ(14,"dt"),t.TgZ(15,"span",6),t._uU(16,"ID"),t.qZA(),t.qZA(),t._uU(17,"\n        "),t.TgZ(18,"dd"),t._uU(19,"\n          "),t.TgZ(20,"span"),t._uU(21),t.qZA(),t._uU(22,"\n        "),t.qZA(),t._uU(23,"\n        "),t.TgZ(24,"dt"),t.TgZ(25,"span",7),t._uU(26,"Number"),t.qZA(),t.qZA(),t._uU(27,"\n        "),t.TgZ(28,"dd"),t._uU(29,"\n          "),t.TgZ(30,"span"),t._uU(31),t.qZA(),t._uU(32,"\n        "),t.qZA(),t._uU(33,"\n        "),t.TgZ(34,"dt"),t.TgZ(35,"span",8),t._uU(36,"Unit"),t.qZA(),t.qZA(),t._uU(37,"\n        "),t.TgZ(38,"dd"),t._uU(39,"\n          "),t.YNc(40,tt,5,4,"div",2),t._uU(41,"\n        "),t.qZA(),t._uU(42,"\n        "),t.TgZ(43,"dt"),t.TgZ(44,"span",9),t._uU(45,"Bonus"),t.qZA(),t.qZA(),t._uU(46,"\n        "),t.TgZ(47,"dd"),t._uU(48,"\n          "),t.YNc(49,et,5,5,"span",10),t._uU(50,"\n        "),t.qZA(),t._uU(51,"\n      "),t.qZA(),t._uU(52,"\n\n      "),t.TgZ(53,"button",11),t.NdJ("click",function(){return t.CHM(n),t.oxw().previousState()}),t._uU(54,"\n        "),t._UZ(55,"fa-icon",12),t._uU(56,"\xa0"),t.TgZ(57,"span",13),t._uU(58,"Back"),t.qZA(),t._uU(59,"\n      "),t.qZA(),t._uU(60,"\n\n      "),t.TgZ(61,"button",14),t._uU(62,"\n        "),t._UZ(63,"fa-icon",15),t._uU(64,"\xa0"),t.TgZ(65,"span",16),t._uU(66,"Edit"),t.qZA(),t._uU(67,"\n      "),t.qZA(),t._uU(68,"\n    "),t.qZA()}if(2&e){const n=t.oxw();t.xp6(21),t.Oqu(n.battleUnit.id),t.xp6(10),t.Oqu(n.battleUnit.number),t.xp6(9),t.Q6J("ngIf",n.battleUnit.unit),t.xp6(9),t.Q6J("ngForOf",n.battleUnit.bonuses),t.xp6(12),t.Q6J("routerLink",t.VKq(5,it,n.battleUnit.id))}}let ot=(()=>{class e{constructor(n){this.activatedRoute=n,this.battleUnit=null}ngOnInit(){this.activatedRoute.data.subscribe(({battleUnit:n})=>{this.battleUnit=n})}previousState(){window.history.back()}}return e.\u0275fac=function(n){return new(n||e)(t.Y36(s.gz))},e.\u0275cmp=t.Xpm({type:e,selectors:[["jhi-battle-unit-detail"]],decls:8,vars:1,consts:[[1,"row","justify-content-center"],[1,"col-8"],[4,"ngIf"],["data-cy","battleUnitDetailsHeading"],["jhiTranslate","totalbattlecalcApp.battleUnit.detail.title"],[1,"row-md","jh-entity-details"],["jhiTranslate","global.field.id"],["jhiTranslate","totalbattlecalcApp.battleUnit.number"],["jhiTranslate","totalbattlecalcApp.battleUnit.unit"],["jhiTranslate","totalbattlecalcApp.battleUnit.bonus"],[4,"ngFor","ngForOf"],["type","submit","data-cy","entityDetailsBackButton",1,"btn","btn-info",3,"click"],["icon","arrow-left"],["jhiTranslate","entity.action.back"],["type","button",1,"btn","btn-primary",3,"routerLink"],["icon","pencil-alt"],["jhiTranslate","entity.action.edit"],[3,"routerLink"]],template:function(n,i){1&n&&(t.TgZ(0,"div",0),t._uU(1,"\n  "),t.TgZ(2,"div",1),t._uU(3,"\n    "),t.YNc(4,at,69,7,"div",2),t._uU(5,"\n  "),t.qZA(),t._uU(6,"\n"),t.qZA(),t._uU(7,"\n")),2&n&&(t.xp6(4),t.Q6J("ngIf",i.battleUnit))},directives:[U.O5,h.P,Z.A,C.w,U.sg,v.BN,s.rH,s.yS],encapsulation:2}),e})();var lt=l(7813),f=l(8561),rt=l(6995),ut=l(1821);function st(e,o){if(1&e&&(t.TgZ(0,"option",12),t._uU(1),t.qZA()),2&e){const n=o.$implicit,i=t.oxw();t.Q6J("ngValue",n.id===(null==i.editForm.get("unit").value?null:i.editForm.get("unit").value.id)?i.editForm.get("unit").value:n),t.xp6(1),t.hij("\n              ",n.id,"\n            ")}}function ct(e,o){if(1&e&&(t.TgZ(0,"option",12),t._uU(1),t.qZA()),2&e){const n=o.$implicit,i=t.oxw();t.Q6J("ngValue",i.getSelectedBonus(n,i.editForm.get("bonuses").value)),t.xp6(1),t.hij("\n              ",n.id,"\n            ")}}let S=(()=>{class e{constructor(n,i,a,r,c){this.battleUnitService=n,this.unitService=i,this.bonusService=a,this.activatedRoute=r,this.fb=c,this.isSaving=!1,this.unitsSharedCollection=[],this.bonusesSharedCollection=[],this.editForm=this.fb.group({id:[],number:[],unit:[],bonuses:[]})}ngOnInit(){this.activatedRoute.data.subscribe(({battleUnit:n})=>{this.updateForm(n),this.loadRelationshipsOptions()})}previousState(){window.history.back()}save(){this.isSaving=!0;const n=this.createFromForm();this.subscribeToSaveResponse(void 0!==n.id?this.battleUnitService.update(n):this.battleUnitService.create(n))}trackUnitById(n,i){return i.id}trackBonusById(n,i){return i.id}getSelectedBonus(n,i){if(i)for(const a of i)if(n.id===a.id)return a;return n}subscribeToSaveResponse(n){n.pipe((0,lt.x)(()=>this.onSaveFinalize())).subscribe(()=>this.onSaveSuccess(),()=>this.onSaveError())}onSaveSuccess(){this.previousState()}onSaveError(){}onSaveFinalize(){this.isSaving=!1}updateForm(n){var i;this.editForm.patchValue({id:n.id,number:n.number,unit:n.unit,bonuses:n.bonuses}),this.unitsSharedCollection=this.unitService.addUnitToCollectionIfMissing(this.unitsSharedCollection,n.unit),this.bonusesSharedCollection=this.bonusService.addBonusToCollectionIfMissing(this.bonusesSharedCollection,...null!==(i=n.bonuses)&&void 0!==i?i:[])}loadRelationshipsOptions(){this.unitService.query().pipe((0,f.U)(n=>{var i;return null!==(i=n.body)&&void 0!==i?i:[]})).pipe((0,f.U)(n=>this.unitService.addUnitToCollectionIfMissing(n,this.editForm.get("unit").value))).subscribe(n=>this.unitsSharedCollection=n),this.bonusService.query().pipe((0,f.U)(n=>{var i;return null!==(i=n.body)&&void 0!==i?i:[]})).pipe((0,f.U)(n=>{var i;return this.bonusService.addBonusToCollectionIfMissing(n,...null!==(i=this.editForm.get("bonuses").value)&&void 0!==i?i:[])})).subscribe(n=>this.bonusesSharedCollection=n)}createFromForm(){return Object.assign(Object.assign({},new B),{id:this.editForm.get(["id"]).value,number:this.editForm.get(["number"]).value,unit:this.editForm.get(["unit"]).value,bonuses:this.editForm.get(["bonuses"]).value})}}return e.\u0275fac=function(n){return new(n||e)(t.Y36(b),t.Y36(rt._),t.Y36(ut.Q),t.Y36(s.gz),t.Y36(u.qu))},e.\u0275cmp=t.Xpm({type:e,selectors:[["jhi-battle-unit-update"]],decls:76,vars:9,consts:[[1,"row","justify-content-center"],[1,"col-8"],["name","editForm","role","form","novalidate","",3,"formGroup","ngSubmit"],["id","jhi-battle-unit-heading","data-cy","BattleUnitCreateUpdateHeading","jhiTranslate","totalbattlecalcApp.battleUnit.home.createOrEditLabel"],[1,"form-group",3,"hidden"],["jhiTranslate","global.field.id","for","field_id",1,"form-control-label"],["type","number","name","id","id","field_id","data-cy","id","formControlName","id",1,"form-control",3,"readonly"],[1,"form-group"],["jhiTranslate","totalbattlecalcApp.battleUnit.number","for","field_number",1,"form-control-label"],["type","number","name","number","id","field_number","data-cy","number","formControlName","number",1,"form-control"],["jhiTranslate","totalbattlecalcApp.battleUnit.unit","for","field_unit",1,"form-control-label"],["id","field_unit","data-cy","unit","name","unit","formControlName","unit",1,"form-control"],[3,"ngValue"],[3,"ngValue",4,"ngFor","ngForOf","ngForTrackBy"],["jhiTranslate","totalbattlecalcApp.battleUnit.bonus","for","field_bonuses"],["id","field_bonuses","data-cy","bonus","multiple","","name","bonuses","formControlName","bonuses",1,"form-control"],["type","button","id","cancel-save","data-cy","entityCreateCancelButton",1,"btn","btn-secondary",3,"click"],["icon","ban"],["jhiTranslate","entity.action.cancel"],["type","submit","id","save-entity","data-cy","entityCreateSaveButton",1,"btn","btn-primary",3,"disabled"],["icon","save"],["jhiTranslate","entity.action.save"]],template:function(n,i){1&n&&(t.TgZ(0,"div",0),t._uU(1,"\n  "),t.TgZ(2,"div",1),t._uU(3,"\n    "),t.TgZ(4,"form",2),t.NdJ("ngSubmit",function(){return i.save()}),t._uU(5,"\n      "),t.TgZ(6,"h2",3),t._uU(7,"\n        Create or edit a Battle Unit\n      "),t.qZA(),t._uU(8,"\n\n      "),t.TgZ(9,"div"),t._uU(10,"\n        "),t._UZ(11,"jhi-alert-error"),t._uU(12,"\n\n        "),t.TgZ(13,"div",4),t._uU(14,"\n          "),t.TgZ(15,"label",5),t._uU(16,"ID"),t.qZA(),t._uU(17,"\n          "),t._UZ(18,"input",6),t._uU(19,"\n        "),t.qZA(),t._uU(20,"\n\n        "),t.TgZ(21,"div",7),t._uU(22,"\n          "),t.TgZ(23,"label",8),t._uU(24,"Number"),t.qZA(),t._uU(25,"\n          "),t._UZ(26,"input",9),t._uU(27,"\n        "),t.qZA(),t._uU(28,"\n\n        "),t.TgZ(29,"div",7),t._uU(30,"\n          "),t.TgZ(31,"label",10),t._uU(32,"Unit"),t.qZA(),t._uU(33,"\n          "),t.TgZ(34,"select",11),t._uU(35,"\n            "),t._UZ(36,"option",12),t._uU(37,"\n            "),t.YNc(38,st,2,2,"option",13),t._uU(39,"\n          "),t.qZA(),t._uU(40,"\n        "),t.qZA(),t._uU(41,"\n\n        "),t.TgZ(42,"div",7),t._uU(43,"\n          "),t.TgZ(44,"label",14),t._uU(45,"Bonus"),t.qZA(),t._uU(46,"\n          "),t.TgZ(47,"select",15),t._uU(48,"\n            "),t.YNc(49,ct,2,2,"option",13),t._uU(50,"\n          "),t.qZA(),t._uU(51,"\n        "),t.qZA(),t._uU(52,"\n      "),t.qZA(),t._uU(53,"\n\n      "),t.TgZ(54,"div"),t._uU(55,"\n        "),t.TgZ(56,"button",16),t.NdJ("click",function(){return i.previousState()}),t._uU(57,"\n          "),t._UZ(58,"fa-icon",17),t._uU(59,"\xa0"),t.TgZ(60,"span",18),t._uU(61,"Cancel"),t.qZA(),t._uU(62,"\n        "),t.qZA(),t._uU(63,"\n\n        "),t.TgZ(64,"button",19),t._uU(65,"\n          "),t._UZ(66,"fa-icon",20),t._uU(67,"\xa0"),t.TgZ(68,"span",21),t._uU(69,"Save"),t.qZA(),t._uU(70,"\n        "),t.qZA(),t._uU(71,"\n      "),t.qZA(),t._uU(72,"\n    "),t.qZA(),t._uU(73,"\n  "),t.qZA(),t._uU(74,"\n"),t.qZA(),t._uU(75,"\n")),2&n&&(t.xp6(4),t.Q6J("formGroup",i.editForm),t.xp6(9),t.Q6J("hidden",null==i.editForm.get("id").value),t.xp6(5),t.Q6J("readonly",!0),t.xp6(18),t.Q6J("ngValue",null),t.xp6(2),t.Q6J("ngForOf",i.unitsSharedCollection)("ngForTrackBy",i.trackUnitById),t.xp6(11),t.Q6J("ngForOf",i.bonusesSharedCollection)("ngForTrackBy",i.trackBonusById),t.xp6(15),t.Q6J("disabled",i.editForm.invalid||i.isSaving))},directives:[u._Y,u.JL,u.sg,h.P,Z.A,u.wV,u.Fj,u.JJ,u.u,u.EJ,u.YN,u.Kr,U.sg,u.K7,v.BN],encapsulation:2}),e})();var x=l(267),dt=l(3974),Ut=l(6513);let A=(()=>{class e{constructor(n,i){this.service=n,this.router=i}resolve(n){const i=n.params.id;return i?this.service.find(i).pipe((0,Ut.zg)(a=>a.body?(0,x.of)(a.body):(this.router.navigate(["404"]),dt.E))):(0,x.of)(new B)}}return e.\u0275fac=function(n){return new(n||e)(t.LFG(b),t.LFG(s.F0))},e.\u0275prov=t.Yz7({token:e,factory:e.\u0275fac,providedIn:"root"}),e})();const pt=[{path:"",component:X,data:{defaultSort:"id,asc"},canActivate:[g.Z]},{path:":id/view",component:ot,resolve:{battleUnit:A},canActivate:[g.Z]},{path:"new",component:S,resolve:{battleUnit:A},canActivate:[g.Z]},{path:":id/edit",component:S,resolve:{battleUnit:A},canActivate:[g.Z]}];let _t=(()=>{class e{}return e.\u0275fac=function(n){return new(n||e)},e.\u0275mod=t.oAB({type:e}),e.\u0275inj=t.cJS({imports:[[s.Bz.forChild(pt)],s.Bz]}),e})(),gt=(()=>{class e{}return e.\u0275fac=function(n){return new(n||e)},e.\u0275mod=t.oAB({type:e}),e.\u0275inj=t.cJS({imports:[[j.m,_t]]}),e})()}}]);