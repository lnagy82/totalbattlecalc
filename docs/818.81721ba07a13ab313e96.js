"use strict";(self.webpackChunktotalbattlecalc=self.webpackChunktotalbattlecalc||[]).push([[818],{4991:(C,Z,i)=>{i.d(Z,{gK:()=>e,aW:()=>p,jo:()=>l,_l:()=>f});const e=20,p="asc",l="desc",f="sort"},7354:(C,Z,i)=>{i.d(Z,{b:()=>p});var e=i(1887);const p=l=>{let f=new e.LE;return l&&(Object.keys(l).forEach(r=>{"sort"!==r&&(f=f.set(r,l[r]))}),l.sort&&l.sort.forEach(r=>{f=f.append("sort",r)})),f}},7575:(C,Z,i)=>{function e(l){return null!=l}i.d(Z,{E:()=>e,z:()=>p});const p=l=>isNaN(l)?0:l},2438:(C,Z,i)=>{i.r(Z),i.d(Z,{BonusModule:()=>at});var e=i(1382),p=i(805),l=i(9375),f=i(6905),r=i(4991),t=i(2741),_=i(1821),g=i(2444),b=i(6274),a=i(3324),m=i(4243),c=i(6662),h=i(4477);const y=function(o){return{id:o}};function B(o,d){if(1&o){const n=t.EpF();t.TgZ(0,"form",1),t.NdJ("ngSubmit",function(){t.CHM(n);const u=t.oxw();return u.confirmDelete(u.bonus.id)}),t._uU(1,"\n  "),t.TgZ(2,"div",2),t._uU(3,"\n    "),t.TgZ(4,"h4",3),t._uU(5,"Confirm delete operation"),t.qZA(),t._uU(6,"\n\n    "),t.TgZ(7,"button",4),t.NdJ("click",function(){return t.CHM(n),t.oxw().cancel()}),t._uU(8,"\xd7"),t.qZA(),t._uU(9,"\n  "),t.qZA(),t._uU(10,"\n\n  "),t.TgZ(11,"div",5),t._uU(12,"\n    "),t._UZ(13,"jhi-alert-error"),t._uU(14,"\n\n    "),t.TgZ(15,"p",6),t._uU(16,"\n      Are you sure you want to delete this Bonus?\n    "),t.qZA(),t._uU(17,"\n  "),t.qZA(),t._uU(18,"\n\n  "),t.TgZ(19,"div",7),t._uU(20,"\n    "),t.TgZ(21,"button",8),t.NdJ("click",function(){return t.CHM(n),t.oxw().cancel()}),t._uU(22,"\n      "),t._UZ(23,"fa-icon",9),t._uU(24,"\xa0"),t.TgZ(25,"span",10),t._uU(26,"Cancel"),t.qZA(),t._uU(27,"\n    "),t.qZA(),t._uU(28,"\n\n    "),t.TgZ(29,"button",11),t._uU(30,"\n      "),t._UZ(31,"fa-icon",12),t._uU(32,"\xa0"),t.TgZ(33,"span",13),t._uU(34,"Delete"),t.qZA(),t._uU(35,"\n    "),t.qZA(),t._uU(36,"\n  "),t.qZA(),t._uU(37,"\n"),t.qZA()}if(2&o){const n=t.oxw();t.xp6(15),t.Q6J("translateValues",t.VKq(1,y,n.bonus.id))}}let A=(()=>{class o{constructor(n,s){this.bonusService=n,this.activeModal=s}cancel(){this.activeModal.dismiss()}confirmDelete(n){this.bonusService.delete(n).subscribe(()=>{this.activeModal.close("deleted")})}}return o.\u0275fac=function(n){return new(n||o)(t.Y36(_.Q),t.Y36(g.Kz))},o.\u0275cmp=t.Xpm({type:o,selectors:[["ng-component"]],decls:2,vars:1,consts:[["name","deleteForm",3,"ngSubmit",4,"ngIf"],["name","deleteForm",3,"ngSubmit"],[1,"modal-header"],["data-cy","bonusDeleteDialogHeading","jhiTranslate","entity.delete.title",1,"modal-title"],["type","button","data-dismiss","modal","aria-hidden","true",1,"close",3,"click"],[1,"modal-body"],["id","jhi-delete-bonus-heading","jhiTranslate","totalbattlecalcApp.bonus.delete.question",3,"translateValues"],[1,"modal-footer"],["type","button","data-dismiss","modal",1,"btn","btn-secondary",3,"click"],["icon","ban"],["jhiTranslate","entity.action.cancel"],["id","jhi-confirm-delete-bonus","data-cy","entityConfirmDeleteButton","type","submit",1,"btn","btn-danger"],["icon","times"],["jhiTranslate","entity.action.delete"]],template:function(n,s){1&n&&(t.YNc(0,B,38,3,"form",0),t._uU(1,"\n")),2&n&&t.Q6J("ngIf",s.bonus)},directives:[b.O5,a._Y,a.JL,a.F,m.P,c.A,h.BN],encapsulation:2}),o})();var D=i(1066),U=i(6059),O=i(9044),x=i(4150);function P(o,d){1&o&&(t.TgZ(0,"div",12),t._uU(1,"\n    "),t.TgZ(2,"span",13),t._uU(3,"No bonuses found"),t.qZA(),t._uU(4,"\n  "),t.qZA())}const T=function(o){return["/bonus",o,"view"]},E=function(o){return["/bonus",o,"edit"]};function q(o,d){if(1&o){const n=t.EpF();t.TgZ(0,"tr",28),t._uU(1,"\n          "),t.TgZ(2,"td"),t._uU(3,"\n            "),t.TgZ(4,"a",29),t._uU(5),t.qZA(),t._uU(6,"\n          "),t.qZA(),t._uU(7,"\n          "),t.TgZ(8,"td",30),t._uU(9),t.qZA(),t._uU(10,"\n          "),t.TgZ(11,"td"),t._uU(12),t.qZA(),t._uU(13,"\n          "),t.TgZ(14,"td",30),t._uU(15),t.qZA(),t._uU(16,"\n          "),t.TgZ(17,"td",31),t._uU(18,"\n            "),t.TgZ(19,"div",32),t._uU(20,"\n              "),t.TgZ(21,"button",33),t._uU(22,"\n                "),t._UZ(23,"fa-icon",34),t._uU(24,"\n                "),t.TgZ(25,"span",35),t._uU(26,"View"),t.qZA(),t._uU(27,"\n              "),t.qZA(),t._uU(28,"\n\n              "),t.TgZ(29,"button",36),t._uU(30,"\n                "),t._UZ(31,"fa-icon",37),t._uU(32,"\n                "),t.TgZ(33,"span",38),t._uU(34,"Edit"),t.qZA(),t._uU(35,"\n              "),t.qZA(),t._uU(36,"\n\n              "),t.TgZ(37,"button",39),t.NdJ("click",function(){const v=t.CHM(n).$implicit;return t.oxw(2).delete(v)}),t._uU(38,"\n                "),t._UZ(39,"fa-icon",40),t._uU(40,"\n                "),t.TgZ(41,"span",41),t._uU(42,"Delete"),t.qZA(),t._uU(43,"\n              "),t.qZA(),t._uU(44,"\n            "),t.qZA(),t._uU(45,"\n          "),t.qZA(),t._uU(46,"\n        "),t.qZA()}if(2&o){const n=d.$implicit;t.xp6(4),t.Q6J("routerLink",t.VKq(9,T,n.id)),t.xp6(1),t.Oqu(n.id),t.xp6(3),t.s9C("jhiTranslate","totalbattlecalcApp.BonusName."+n.name),t.xp6(1),t.Oqu(n.name),t.xp6(3),t.Oqu(n.value),t.xp6(2),t.s9C("jhiTranslate","totalbattlecalcApp.MeasurementUnit."+n.unit),t.xp6(1),t.Oqu(n.unit),t.xp6(6),t.Q6J("routerLink",t.VKq(11,T,n.id)),t.xp6(8),t.Q6J("routerLink",t.VKq(13,E,n.id))}}function S(o,d){if(1&o){const n=t.EpF();t.TgZ(0,"div",14),t._uU(1,"\n    "),t.TgZ(2,"table",15),t._uU(3,"\n      "),t.TgZ(4,"thead"),t._uU(5,"\n        "),t.TgZ(6,"tr",16),t.NdJ("predicateChange",function(u){return t.CHM(n),t.oxw().predicate=u})("ascendingChange",function(u){return t.CHM(n),t.oxw().ascending=u})("sortChange",function(){return t.CHM(n),t.oxw().loadPage()}),t._uU(7,"\n          "),t.TgZ(8,"th",17),t.TgZ(9,"span",18),t._uU(10,"ID"),t.qZA(),t._uU(11," "),t._UZ(12,"fa-icon",19),t.qZA(),t._uU(13,"\n          "),t.TgZ(14,"th",20),t._uU(15,"\n            "),t.TgZ(16,"span",21),t._uU(17,"Name"),t.qZA(),t._uU(18," "),t._UZ(19,"fa-icon",19),t._uU(20,"\n          "),t.qZA(),t._uU(21,"\n          "),t.TgZ(22,"th",22),t._uU(23,"\n            "),t.TgZ(24,"span",23),t._uU(25,"Value"),t.qZA(),t._uU(26," "),t._UZ(27,"fa-icon",19),t._uU(28,"\n          "),t.qZA(),t._uU(29,"\n          "),t.TgZ(30,"th",24),t._uU(31,"\n            "),t.TgZ(32,"span",25),t._uU(33,"Unit"),t.qZA(),t._uU(34," "),t._UZ(35,"fa-icon",19),t._uU(36,"\n          "),t.qZA(),t._uU(37,"\n          "),t._UZ(38,"th",26),t._uU(39,"\n        "),t.qZA(),t._uU(40,"\n      "),t.qZA(),t._uU(41,"\n      "),t.TgZ(42,"tbody"),t._uU(43,"\n        "),t.YNc(44,q,47,15,"tr",27),t._uU(45,"\n      "),t.qZA(),t._uU(46,"\n    "),t.qZA(),t._uU(47,"\n  "),t.qZA()}if(2&o){const n=t.oxw();t.xp6(6),t.Q6J("predicate",n.predicate)("ascending",n.ascending),t.xp6(38),t.Q6J("ngForOf",n.bonuses)("ngForTrackBy",n.trackId)}}const w=function(o,d,n){return{page:o,totalItems:d,itemsPerPage:n}};function H(o,d){if(1&o){const n=t.EpF();t.TgZ(0,"div"),t._uU(1,"\n    "),t.TgZ(2,"div",42),t._uU(3,"\n      "),t._UZ(4,"jhi-item-count",43),t._uU(5,"\n    "),t.qZA(),t._uU(6,"\n\n    "),t.TgZ(7,"div",42),t._uU(8,"\n      "),t.TgZ(9,"ngb-pagination",44),t.NdJ("pageChange",function(u){return t.CHM(n),t.oxw().ngbPaginationPage=u})("pageChange",function(u){return t.CHM(n),t.oxw().loadPage(u)}),t.qZA(),t._uU(10,"\n    "),t.qZA(),t._uU(11,"\n  "),t.qZA()}if(2&o){const n=t.oxw();t.xp6(4),t.Q6J("params",t.kEZ(7,w,n.page,n.totalItems,n.itemsPerPage)),t.xp6(5),t.Q6J("collectionSize",n.totalItems)("page",n.ngbPaginationPage)("pageSize",n.itemsPerPage)("maxSize",5)("rotate",!0)("boundaryLinks",!0)}}const Y=function(){return["/bonus/new"]};let K=(()=>{class o{constructor(n,s,u,v){this.bonusService=n,this.activatedRoute=s,this.router=u,this.modalService=v,this.isLoading=!1,this.totalItems=0,this.itemsPerPage=r.gK,this.ngbPaginationPage=1}loadPage(n,s){var u;this.isLoading=!0;const v=null!==(u=null!=n?n:this.page)&&void 0!==u?u:1;this.bonusService.query({page:v-1,size:this.itemsPerPage,sort:this.sort()}).subscribe(j=>{this.isLoading=!1,this.onSuccess(j.body,j.headers,v,!s)},()=>{this.isLoading=!1,this.onError()})}ngOnInit(){this.handleNavigation()}trackId(n,s){return s.id}delete(n){const s=this.modalService.open(A,{size:"lg",backdrop:"static"});s.componentInstance.bonus=n,s.closed.subscribe(u=>{"deleted"===u&&this.loadPage()})}sort(){const n=[this.predicate+","+(this.ascending?r.aW:r.jo)];return"id"!==this.predicate&&n.push("id"),n}handleNavigation(){(0,f.aj)([this.activatedRoute.data,this.activatedRoute.queryParamMap]).subscribe(([n,s])=>{var u;const v=s.get("page"),j=+(null!=v?v:1),J=(null!==(u=s.get(r._l))&&void 0!==u?u:n.defaultSort).split(","),R=J[0],Q=J[1]===r.aW;(j!==this.page||R!==this.predicate||Q!==this.ascending)&&(this.predicate=R,this.ascending=Q,this.loadPage(j,!0))})}onSuccess(n,s,u,v){this.totalItems=Number(s.get("X-Total-Count")),this.page=u,v&&this.router.navigate(["/bonus"],{queryParams:{page:this.page,size:this.itemsPerPage,sort:this.predicate+","+(this.ascending?r.aW:r.jo)}}),this.bonuses=null!=n?n:[],this.ngbPaginationPage=this.page}onError(){var n;this.ngbPaginationPage=null!==(n=this.page)&&void 0!==n?n:1}}return o.\u0275fac=function(n){return new(n||o)(t.Y36(_.Q),t.Y36(p.gz),t.Y36(p.F0),t.Y36(g.FF))},o.\u0275cmp=t.Xpm({type:o,selectors:[["jhi-bonus"]],decls:38,vars:7,consts:[["id","page-heading","data-cy","BonusHeading"],["jhiTranslate","totalbattlecalcApp.bonus.home.title"],[1,"d-flex","justify-content-end"],[1,"btn","btn-info","mr-2",3,"disabled","click"],["icon","sync",3,"spin"],["jhiTranslate","totalbattlecalcApp.bonus.home.refreshListLabel"],["id","jh-create-entity","data-cy","entityCreateButton",1,"btn","btn-primary","jh-create-entity","create-bonus",3,"routerLink"],["icon","plus"],["jhiTranslate","totalbattlecalcApp.bonus.home.createLabel"],["class","alert alert-warning","id","no-result",4,"ngIf"],["class","table-responsive","id","entities",4,"ngIf"],[4,"ngIf"],["id","no-result",1,"alert","alert-warning"],["jhiTranslate","totalbattlecalcApp.bonus.home.notFound"],["id","entities",1,"table-responsive"],["aria-describedby","page-heading",1,"table","table-striped"],["jhiSort","",3,"predicate","ascending","predicateChange","ascendingChange","sortChange"],["scope","col","jhiSortBy","id"],["jhiTranslate","global.field.id"],["icon","sort"],["scope","col","jhiSortBy","name"],["jhiTranslate","totalbattlecalcApp.bonus.name"],["scope","col","jhiSortBy","value"],["jhiTranslate","totalbattlecalcApp.bonus.value"],["scope","col","jhiSortBy","unit"],["jhiTranslate","totalbattlecalcApp.bonus.unit"],["scope","col"],["data-cy","entityTable",4,"ngFor","ngForOf","ngForTrackBy"],["data-cy","entityTable"],[3,"routerLink"],[3,"jhiTranslate"],[1,"text-right"],[1,"btn-group"],["type","submit","data-cy","entityDetailsButton",1,"btn","btn-info","btn-sm",3,"routerLink"],["icon","eye"],["jhiTranslate","entity.action.view",1,"d-none","d-md-inline"],["type","submit","data-cy","entityEditButton",1,"btn","btn-primary","btn-sm",3,"routerLink"],["icon","pencil-alt"],["jhiTranslate","entity.action.edit",1,"d-none","d-md-inline"],["type","submit","data-cy","entityDeleteButton",1,"btn","btn-danger","btn-sm",3,"click"],["icon","times"],["jhiTranslate","entity.action.delete",1,"d-none","d-md-inline"],[1,"row","justify-content-center"],[3,"params"],[3,"collectionSize","page","pageSize","maxSize","rotate","boundaryLinks","pageChange"]],template:function(n,s){1&n&&(t.TgZ(0,"div"),t._uU(1,"\n  "),t.TgZ(2,"h2",0),t._uU(3,"\n    "),t.TgZ(4,"span",1),t._uU(5,"Bonuses"),t.qZA(),t._uU(6,"\n\n    "),t.TgZ(7,"div",2),t._uU(8,"\n      "),t.TgZ(9,"button",3),t.NdJ("click",function(){return s.loadPage()}),t._uU(10,"\n        "),t._UZ(11,"fa-icon",4),t._uU(12,"\n        "),t.TgZ(13,"span",5),t._uU(14,"Refresh List"),t.qZA(),t._uU(15,"\n      "),t.qZA(),t._uU(16,"\n\n      "),t.TgZ(17,"button",6),t._uU(18,"\n        "),t._UZ(19,"fa-icon",7),t._uU(20,"\n        "),t.TgZ(21,"span",8),t._uU(22," Create a new Bonus "),t.qZA(),t._uU(23,"\n      "),t.qZA(),t._uU(24,"\n    "),t.qZA(),t._uU(25,"\n  "),t.qZA(),t._uU(26,"\n\n  "),t._UZ(27,"jhi-alert-error"),t._uU(28,"\n\n  "),t._UZ(29,"jhi-alert"),t._uU(30,"\n\n  "),t.YNc(31,P,5,0,"div",9),t._uU(32,"\n\n  "),t.YNc(33,S,48,4,"div",10),t._uU(34,"\n\n  "),t.YNc(35,H,12,11,"div",11),t._uU(36,"\n"),t.qZA(),t._uU(37,"\n")),2&n&&(t.xp6(9),t.Q6J("disabled",s.isLoading),t.xp6(2),t.Q6J("spin",s.isLoading),t.xp6(6),t.Q6J("routerLink",t.DdM(6,Y)),t.xp6(14),t.Q6J("ngIf",0===(null==s.bonuses?null:s.bonuses.length)),t.xp6(2),t.Q6J("ngIf",s.bonuses&&s.bonuses.length>0),t.xp6(2),t.Q6J("ngIf",s.bonuses&&s.bonuses.length>0))},directives:[m.P,h.BN,p.rH,c.A,D.w,b.O5,U.b,O.T,b.sg,p.yS,x.N,g.N9],encapsulation:2}),o})();const W=function(o){return["/bonus",o,"edit"]};function k(o,d){if(1&o){const n=t.EpF();t.TgZ(0,"div"),t._uU(1,"\n      "),t.TgZ(2,"h2",3),t.TgZ(3,"span",4),t._uU(4,"Bonus"),t.qZA(),t.qZA(),t._uU(5,"\n\n      "),t._UZ(6,"hr"),t._uU(7,"\n\n      "),t._UZ(8,"jhi-alert-error"),t._uU(9,"\n\n      "),t._UZ(10,"jhi-alert"),t._uU(11,"\n\n      "),t.TgZ(12,"dl",5),t._uU(13,"\n        "),t.TgZ(14,"dt"),t.TgZ(15,"span",6),t._uU(16,"ID"),t.qZA(),t.qZA(),t._uU(17,"\n        "),t.TgZ(18,"dd"),t._uU(19,"\n          "),t.TgZ(20,"span"),t._uU(21),t.qZA(),t._uU(22,"\n        "),t.qZA(),t._uU(23,"\n        "),t.TgZ(24,"dt"),t.TgZ(25,"span",7),t._uU(26,"Name"),t.qZA(),t.qZA(),t._uU(27,"\n        "),t.TgZ(28,"dd"),t._uU(29,"\n          "),t.TgZ(30,"span",8),t._uU(31),t.qZA(),t._uU(32,"\n        "),t.qZA(),t._uU(33,"\n        "),t.TgZ(34,"dt"),t.TgZ(35,"span",9),t._uU(36,"Value"),t.qZA(),t.qZA(),t._uU(37,"\n        "),t.TgZ(38,"dd"),t._uU(39,"\n          "),t.TgZ(40,"span"),t._uU(41),t.qZA(),t._uU(42,"\n        "),t.qZA(),t._uU(43,"\n        "),t.TgZ(44,"dt"),t.TgZ(45,"span",10),t._uU(46,"Unit"),t.qZA(),t.qZA(),t._uU(47,"\n        "),t.TgZ(48,"dd"),t._uU(49,"\n          "),t.TgZ(50,"span",8),t._uU(51),t.qZA(),t._uU(52,"\n        "),t.qZA(),t._uU(53,"\n      "),t.qZA(),t._uU(54,"\n\n      "),t.TgZ(55,"button",11),t.NdJ("click",function(){return t.CHM(n),t.oxw().previousState()}),t._uU(56,"\n        "),t._UZ(57,"fa-icon",12),t._uU(58,"\xa0"),t.TgZ(59,"span",13),t._uU(60,"Back"),t.qZA(),t._uU(61,"\n      "),t.qZA(),t._uU(62,"\n\n      "),t.TgZ(63,"button",14),t._uU(64,"\n        "),t._UZ(65,"fa-icon",15),t._uU(66,"\xa0"),t.TgZ(67,"span",16),t._uU(68,"Edit"),t.qZA(),t._uU(69,"\n      "),t.qZA(),t._uU(70,"\n    "),t.qZA()}if(2&o){const n=t.oxw();t.xp6(21),t.Oqu(n.bonus.id),t.xp6(9),t.s9C("jhiTranslate","totalbattlecalcApp.BonusName."+n.bonus.name),t.xp6(1),t.Oqu(n.bonus.name),t.xp6(10),t.Oqu(n.bonus.value),t.xp6(9),t.s9C("jhiTranslate","totalbattlecalcApp.MeasurementUnit."+n.bonus.unit),t.xp6(1),t.Oqu(n.bonus.unit),t.xp6(12),t.Q6J("routerLink",t.VKq(7,W,n.bonus.id))}}let z=(()=>{class o{constructor(n){this.activatedRoute=n,this.bonus=null}ngOnInit(){this.activatedRoute.data.subscribe(({bonus:n})=>{this.bonus=n})}previousState(){window.history.back()}}return o.\u0275fac=function(n){return new(n||o)(t.Y36(p.gz))},o.\u0275cmp=t.Xpm({type:o,selectors:[["jhi-bonus-detail"]],decls:8,vars:1,consts:[[1,"row","justify-content-center"],[1,"col-8"],[4,"ngIf"],["data-cy","bonusDetailsHeading"],["jhiTranslate","totalbattlecalcApp.bonus.detail.title"],[1,"row-md","jh-entity-details"],["jhiTranslate","global.field.id"],["jhiTranslate","totalbattlecalcApp.bonus.name"],[3,"jhiTranslate"],["jhiTranslate","totalbattlecalcApp.bonus.value"],["jhiTranslate","totalbattlecalcApp.bonus.unit"],["type","submit","data-cy","entityDetailsBackButton",1,"btn","btn-info",3,"click"],["icon","arrow-left"],["jhiTranslate","entity.action.back"],["type","button",1,"btn","btn-primary",3,"routerLink"],["icon","pencil-alt"],["jhiTranslate","entity.action.edit"]],template:function(n,s){1&n&&(t.TgZ(0,"div",0),t._uU(1,"\n  "),t.TgZ(2,"div",1),t._uU(3,"\n    "),t.YNc(4,k,71,9,"div",2),t._uU(5,"\n  "),t.qZA(),t._uU(6,"\n"),t.qZA(),t._uU(7,"\n")),2&n&&(t.xp6(4),t.Q6J("ngIf",s.bonus))},directives:[b.O5,m.P,c.A,D.w,h.BN,p.rH],encapsulation:2}),o})();var V=i(7813),L=i(226),M=(()=>{return(o=M||(M={})).STRENGTH="STRENGTH",o.HEALTH="HEALTH",o.MARCH_SPEED="MARCH_SPEED",o.CARRYING_CAPACITY="CARRYING_CAPACITY",M;var o})(),$=i(1426),G=i(1158);function X(o,d){if(1&o&&(t.TgZ(0,"option",22),t._uU(1),t.ALo(2,"translate"),t.qZA()),2&o){const n=d.$implicit;t.Q6J("value",n),t.xp6(1),t.hij("\n              ",t.lcZ(2,2,"totalbattlecalcApp.BonusName."+n),"\n            ")}}function tt(o,d){if(1&o&&(t.TgZ(0,"option",22),t._uU(1),t.ALo(2,"translate"),t.qZA()),2&o){const n=d.$implicit;t.Q6J("value",n),t.xp6(1),t.hij("\n              ",t.lcZ(2,2,"totalbattlecalcApp.MeasurementUnit."+n),"\n            ")}}let F=(()=>{class o{constructor(n,s,u){this.bonusService=n,this.activatedRoute=s,this.fb=u,this.isSaving=!1,this.bonusNameValues=Object.keys(M),this.measurementUnitValues=Object.keys($.q),this.editForm=this.fb.group({id:[],name:[],value:[],unit:[]})}ngOnInit(){this.activatedRoute.data.subscribe(({bonus:n})=>{this.updateForm(n)})}previousState(){window.history.back()}save(){this.isSaving=!0;const n=this.createFromForm();this.subscribeToSaveResponse(void 0!==n.id?this.bonusService.update(n):this.bonusService.create(n))}subscribeToSaveResponse(n){n.pipe((0,V.x)(()=>this.onSaveFinalize())).subscribe(()=>this.onSaveSuccess(),()=>this.onSaveError())}onSaveSuccess(){this.previousState()}onSaveError(){}onSaveFinalize(){this.isSaving=!1}updateForm(n){this.editForm.patchValue({id:n.id,name:n.name,value:n.value,unit:n.unit})}createFromForm(){return Object.assign(Object.assign({},new L.r),{id:this.editForm.get(["id"]).value,name:this.editForm.get(["name"]).value,value:this.editForm.get(["value"]).value,unit:this.editForm.get(["unit"]).value})}}return o.\u0275fac=function(n){return new(n||o)(t.Y36(_.Q),t.Y36(p.gz),t.Y36(a.qu))},o.\u0275cmp=t.Xpm({type:o,selectors:[["jhi-bonus-update"]],decls:82,vars:14,consts:[[1,"row","justify-content-center"],[1,"col-8"],["name","editForm","role","form","novalidate","",3,"formGroup","ngSubmit"],["id","jhi-bonus-heading","data-cy","BonusCreateUpdateHeading","jhiTranslate","totalbattlecalcApp.bonus.home.createOrEditLabel"],[1,"form-group",3,"hidden"],["jhiTranslate","global.field.id","for","field_id",1,"form-control-label"],["type","number","name","id","id","field_id","data-cy","id","formControlName","id",1,"form-control",3,"readonly"],[1,"form-group"],["jhiTranslate","totalbattlecalcApp.bonus.name","for","field_name",1,"form-control-label"],["name","name","formControlName","name","id","field_name","data-cy","name",1,"form-control"],[3,"ngValue"],[3,"value",4,"ngFor","ngForOf"],["jhiTranslate","totalbattlecalcApp.bonus.value","for","field_value",1,"form-control-label"],["type","number","name","value","id","field_value","data-cy","value","formControlName","value",1,"form-control"],["jhiTranslate","totalbattlecalcApp.bonus.unit","for","field_unit",1,"form-control-label"],["name","unit","formControlName","unit","id","field_unit","data-cy","unit",1,"form-control"],["type","button","id","cancel-save","data-cy","entityCreateCancelButton",1,"btn","btn-secondary",3,"click"],["icon","ban"],["jhiTranslate","entity.action.cancel"],["type","submit","id","save-entity","data-cy","entityCreateSaveButton",1,"btn","btn-primary",3,"disabled"],["icon","save"],["jhiTranslate","entity.action.save"],[3,"value"]],template:function(n,s){1&n&&(t.TgZ(0,"div",0),t._uU(1,"\n  "),t.TgZ(2,"div",1),t._uU(3,"\n    "),t.TgZ(4,"form",2),t.NdJ("ngSubmit",function(){return s.save()}),t._uU(5,"\n      "),t.TgZ(6,"h2",3),t._uU(7,"\n        Create or edit a Bonus\n      "),t.qZA(),t._uU(8,"\n\n      "),t.TgZ(9,"div"),t._uU(10,"\n        "),t._UZ(11,"jhi-alert-error"),t._uU(12,"\n\n        "),t.TgZ(13,"div",4),t._uU(14,"\n          "),t.TgZ(15,"label",5),t._uU(16,"ID"),t.qZA(),t._uU(17,"\n          "),t._UZ(18,"input",6),t._uU(19,"\n        "),t.qZA(),t._uU(20,"\n\n        "),t.TgZ(21,"div",7),t._uU(22,"\n          "),t.TgZ(23,"label",8),t._uU(24,"Name"),t.qZA(),t._uU(25,"\n          "),t.TgZ(26,"select",9),t._uU(27,"\n            "),t.TgZ(28,"option",10),t._uU(29),t.ALo(30,"translate"),t.qZA(),t._uU(31,"\n            "),t.YNc(32,X,3,4,"option",11),t._uU(33,"\n          "),t.qZA(),t._uU(34,"\n        "),t.qZA(),t._uU(35,"\n\n        "),t.TgZ(36,"div",7),t._uU(37,"\n          "),t.TgZ(38,"label",12),t._uU(39,"Value"),t.qZA(),t._uU(40,"\n          "),t._UZ(41,"input",13),t._uU(42,"\n        "),t.qZA(),t._uU(43,"\n\n        "),t.TgZ(44,"div",7),t._uU(45,"\n          "),t.TgZ(46,"label",14),t._uU(47,"Unit"),t.qZA(),t._uU(48,"\n          "),t.TgZ(49,"select",15),t._uU(50,"\n            "),t.TgZ(51,"option",10),t._uU(52),t.ALo(53,"translate"),t.qZA(),t._uU(54,"\n            "),t.YNc(55,tt,3,4,"option",11),t._uU(56,"\n          "),t.qZA(),t._uU(57,"\n        "),t.qZA(),t._uU(58,"\n      "),t.qZA(),t._uU(59,"\n\n      "),t.TgZ(60,"div"),t._uU(61,"\n        "),t.TgZ(62,"button",16),t.NdJ("click",function(){return s.previousState()}),t._uU(63,"\n          "),t._UZ(64,"fa-icon",17),t._uU(65,"\xa0"),t.TgZ(66,"span",18),t._uU(67,"Cancel"),t.qZA(),t._uU(68,"\n        "),t.qZA(),t._uU(69,"\n\n        "),t.TgZ(70,"button",19),t._uU(71,"\n          "),t._UZ(72,"fa-icon",20),t._uU(73,"\xa0"),t.TgZ(74,"span",21),t._uU(75,"Save"),t.qZA(),t._uU(76,"\n        "),t.qZA(),t._uU(77,"\n      "),t.qZA(),t._uU(78,"\n    "),t.qZA(),t._uU(79,"\n  "),t.qZA(),t._uU(80,"\n"),t.qZA(),t._uU(81,"\n")),2&n&&(t.xp6(4),t.Q6J("formGroup",s.editForm),t.xp6(9),t.Q6J("hidden",null==s.editForm.get("id").value),t.xp6(5),t.Q6J("readonly",!0),t.xp6(10),t.Q6J("ngValue",null),t.xp6(1),t.Oqu(t.lcZ(30,10,"totalbattlecalcApp.BonusName.null")),t.xp6(3),t.Q6J("ngForOf",s.bonusNameValues),t.xp6(19),t.Q6J("ngValue",null),t.xp6(1),t.Oqu(t.lcZ(53,12,"totalbattlecalcApp.MeasurementUnit.null")),t.xp6(3),t.Q6J("ngForOf",s.measurementUnitValues),t.xp6(15),t.Q6J("disabled",s.editForm.invalid||s.isSaving))},directives:[a._Y,a.JL,a.sg,m.P,c.A,a.wV,a.Fj,a.JJ,a.u,a.EJ,a.YN,a.Kr,b.sg,h.BN],pipes:[G.X$],encapsulation:2}),o})();var N=i(267),nt=i(3974),et=i(6513);let I=(()=>{class o{constructor(n,s){this.service=n,this.router=s}resolve(n){const s=n.params.id;return s?this.service.find(s).pipe((0,et.zg)(u=>u.body?(0,N.of)(u.body):(this.router.navigate(["404"]),nt.E))):(0,N.of)(new L.r)}}return o.\u0275fac=function(n){return new(n||o)(t.LFG(_.Q),t.LFG(p.F0))},o.\u0275prov=t.Yz7({token:o,factory:o.\u0275fac,providedIn:"root"}),o})();const ot=[{path:"",component:K,data:{defaultSort:"id,asc"},canActivate:[l.Z]},{path:":id/view",component:z,resolve:{bonus:I},canActivate:[l.Z]},{path:"new",component:F,resolve:{bonus:I},canActivate:[l.Z]},{path:":id/edit",component:F,resolve:{bonus:I},canActivate:[l.Z]}];let it=(()=>{class o{}return o.\u0275fac=function(n){return new(n||o)},o.\u0275mod=t.oAB({type:o}),o.\u0275inj=t.cJS({imports:[[p.Bz.forChild(ot)],p.Bz]}),o})(),at=(()=>{class o{}return o.\u0275fac=function(n){return new(n||o)},o.\u0275mod=t.oAB({type:o}),o.\u0275inj=t.cJS({imports:[[e.m,it]]}),o})()},6662:(C,Z,i)=>{i.d(Z,{A:()=>b});var e=i(2741),p=i(6912),l=i(9161),f=i(1158),r=i(6274),t=i(2444);function _(a,m){if(1&a){const c=e.EpF();e.TgZ(0,"ngb-alert",4),e.NdJ("closed",function(){e.CHM(c);const y=e.oxw().$implicit;return e.oxw().close(y)}),e._uU(1,"\n      "),e._UZ(2,"pre",5),e._uU(3,"\n    "),e.qZA()}if(2&a){const c=e.oxw().$implicit;e.Q6J("type",c.type),e.xp6(2),e.Q6J("innerHTML",c.message,e.oJD)}}function g(a,m){if(1&a&&(e.TgZ(0,"div",2),e._uU(1,"\n    "),e.YNc(2,_,4,2,"ngb-alert",3),e._uU(3,"\n  "),e.qZA()),2&a){const c=m.$implicit,h=e.oxw();e.Q6J("ngClass",h.setClasses(c)),e.xp6(2),e.Q6J("ngIf",c.message)}}let b=(()=>{class a{constructor(c,h,y){this.alertService=c,this.eventManager=h,this.alerts=[],this.errorListener=h.subscribe("totalbattlecalcApp.error",B=>{const A=B.content;this.addErrorAlert(A.message,A.key,A.params)}),this.httpErrorListener=h.subscribe("totalbattlecalcApp.httpError",B=>{var A,D;const U=B.content;switch(U.status){case 0:this.addErrorAlert("Server not reachable","error.server.not.reachable");break;case 400:{const O=U.headers.keys();let x=null,P=null;for(const T of O)T.toLowerCase().endsWith("app-error")?x=U.headers.get(T):T.toLowerCase().endsWith("app-params")&&(P=U.headers.get(T));if(x){const T=P?{entityName:y.instant(`global.menu.entities.${P}`)}:void 0;this.addErrorAlert(x,x,T)}else if(""!==U.error&&U.error.fieldErrors){const T=U.error.fieldErrors;for(const E of T){["Min","Max","DecimalMin","DecimalMax"].includes(E.message)&&(E.message="Size");const q=E.field.replace(/\[\d*\]/g,"[]"),S=y.instant(`totalbattlecalcApp.${E.objectName}.${q}`);this.addErrorAlert(`Error on field "${S}"`,`error.${E.message}`,{fieldName:S})}}else""!==U.error&&U.error.message?this.addErrorAlert(null!==(A=U.error.detail)&&void 0!==A?A:U.error.message,U.error.message,U.error.params):this.addErrorAlert(U.error,U.error);break}case 404:this.addErrorAlert("Not found","error.url.not.found");break;default:""!==U.error&&U.error.message?this.addErrorAlert(null!==(D=U.error.detail)&&void 0!==D?D:U.error.message,U.error.message,U.error.params):this.addErrorAlert(U.error,U.error)}})}setClasses(c){const h={"jhi-toast":Boolean(c.toast)};return c.position?Object.assign(Object.assign({},h),{[c.position]:!0}):h}ngOnDestroy(){this.eventManager.destroy(this.errorListener),this.eventManager.destroy(this.httpErrorListener)}close(c){var h;null===(h=c.close)||void 0===h||h.call(c,this.alerts)}addErrorAlert(c,h,y){this.alertService.addAlert({type:"danger",message:c,translationKey:h,translationParams:y},this.alerts)}}return a.\u0275fac=function(c){return new(c||a)(e.Y36(p.c),e.Y36(l.Q),e.Y36(f.sK))},a.\u0275cmp=e.Xpm({type:a,selectors:[["jhi-alert-error"]],decls:5,vars:1,consts:[["role","alert",1,"alerts"],[3,"ngClass",4,"ngFor","ngForOf"],[3,"ngClass"],[3,"type","closed",4,"ngIf"],[3,"type","closed"],[3,"innerHTML"]],template:function(c,h){1&c&&(e.TgZ(0,"div",0),e._uU(1,"\n  "),e.YNc(2,g,4,2,"div",1),e._uU(3,"\n"),e.qZA(),e._uU(4,"\n")),2&c&&(e.xp6(2),e.Q6J("ngForOf",h.alerts))},directives:[r.sg,r.mk,r.O5,t.xm],encapsulation:2}),a})()},1066:(C,Z,i)=>{i.d(Z,{w:()=>_});var e=i(2741),p=i(6912),l=i(6274),f=i(2444);function r(g,b){if(1&g){const a=e.EpF();e.TgZ(0,"ngb-alert",4),e.NdJ("closed",function(){e.CHM(a);const c=e.oxw().$implicit;return e.oxw().close(c)}),e._uU(1,"\n      "),e._UZ(2,"pre",5),e._uU(3,"\n    "),e.qZA()}if(2&g){const a=e.oxw().$implicit;e.Q6J("type",a.type),e.xp6(2),e.Q6J("innerHTML",a.message,e.oJD)}}function t(g,b){if(1&g&&(e.TgZ(0,"div",2),e._uU(1,"\n    "),e.YNc(2,r,4,2,"ngb-alert",3),e._uU(3,"\n  "),e.qZA()),2&g){const a=b.$implicit,m=e.oxw();e.Q6J("ngClass",m.setClasses(a)),e.xp6(2),e.Q6J("ngIf",a.message)}}let _=(()=>{class g{constructor(a){this.alertService=a,this.alerts=[]}ngOnInit(){this.alerts=this.alertService.get()}setClasses(a){const m={"jhi-toast":Boolean(a.toast)};return a.position?Object.assign(Object.assign({},m),{[a.position]:!0}):m}ngOnDestroy(){this.alertService.clear()}close(a){var m;null===(m=a.close)||void 0===m||m.call(a,this.alerts)}}return g.\u0275fac=function(a){return new(a||g)(e.Y36(p.c))},g.\u0275cmp=e.Xpm({type:g,selectors:[["jhi-alert"]],decls:5,vars:1,consts:[["role","alert",1,"alerts"],[3,"ngClass",4,"ngFor","ngForOf"],[3,"ngClass"],[3,"type","closed",4,"ngIf"],[3,"type","closed"],[3,"innerHTML"]],template:function(a,m){1&a&&(e.TgZ(0,"div",0),e._uU(1,"\n  "),e.YNc(2,t,4,2,"div",1),e._uU(3,"\n"),e.qZA(),e._uU(4,"\n")),2&a&&(e.xp6(2),e.Q6J("ngForOf",m.alerts))},directives:[l.sg,l.mk,l.O5,f.xm],encapsulation:2}),g})()},4150:(C,Z,i)=>{i.d(Z,{N:()=>f});var e=i(2741),p=i(4243);const l=function(r,t,_){return{first:r,second:t,total:_}};let f=(()=>{class r{set params(_){_.page&&void 0!==_.totalItems&&_.itemsPerPage?(this.first=(_.page-1)*_.itemsPerPage+1,this.second=_.page*_.itemsPerPage<_.totalItems?_.page*_.itemsPerPage:_.totalItems):(this.first=void 0,this.second=void 0),this.total=_.totalItems}}return r.\u0275fac=function(_){return new(_||r)},r.\u0275cmp=e.Xpm({type:r,selectors:[["jhi-item-count"]],inputs:{params:"params"},decls:3,vars:5,consts:[["jhiTranslate","global.item-count",3,"translateValues"]],template:function(_,g){1&_&&(e._uU(0," "),e._UZ(1,"div",0),e._uU(2," ")),2&_&&(e.xp6(1),e.Q6J("translateValues",e.kEZ(1,l,g.first,g.second,g.total)))},directives:[p.P],encapsulation:2}),r})()},9044:(C,Z,i)=>{i.d(Z,{T:()=>_});var e=i(6006),p=i(7194),l=i(4477),f=i(8036),r=i(2741),t=i(6059);let _=(()=>{class g{constructor(a){this.sort=a,this.sortIcon=f.CmM,this.sortAscIcon=f.foy,this.sortDescIcon=f.u9C,this.destroy$=new e.xQ,a.predicateChange.pipe((0,p.R)(this.destroy$)).subscribe(()=>this.updateIconDefinition()),a.ascendingChange.pipe((0,p.R)(this.destroy$)).subscribe(()=>this.updateIconDefinition())}onClick(){this.iconComponent&&this.sort.sort(this.jhiSortBy)}ngAfterContentInit(){this.updateIconDefinition()}ngOnDestroy(){this.destroy$.next(),this.destroy$.complete()}updateIconDefinition(){if(this.iconComponent){let a=this.sortIcon;this.sort.predicate===this.jhiSortBy&&(a=this.sort.ascending?this.sortAscIcon:this.sortDescIcon),this.iconComponent.icon=a.iconName,this.iconComponent.render()}}}return g.\u0275fac=function(a){return new(a||g)(r.Y36(t.b,1))},g.\u0275dir=r.lG2({type:g,selectors:[["","jhiSortBy",""]],contentQueries:function(a,m,c){if(1&a&&r.Suo(c,l.BN,5),2&a){let h;r.iGM(h=r.CRH())&&(m.iconComponent=h.first)}},hostBindings:function(a,m){1&a&&r.NdJ("click",function(){return m.onClick()})},inputs:{jhiSortBy:"jhiSortBy"}}),g})()},6059:(C,Z,i)=>{i.d(Z,{b:()=>p});var e=i(2741);let p=(()=>{class l{constructor(){this.predicateChange=new e.vpe,this.ascendingChange=new e.vpe,this.sortChange=new e.vpe}get predicate(){return this._predicate}set predicate(r){this._predicate=r,this.predicateChange.emit(r)}get ascending(){return this._ascending}set ascending(r){this._ascending=r,this.ascendingChange.emit(r)}sort(r){this.ascending=r!==this.predicate||!this.ascending,this.predicate=r,this.predicateChange.emit(r),this.ascendingChange.emit(this.ascending),this.sortChange.emit({predicate:this.predicate,ascending:this.ascending})}}return l.\u0275fac=function(r){return new(r||l)},l.\u0275dir=e.lG2({type:l,selectors:[["","jhiSort",""]],inputs:{predicate:"predicate",ascending:"ascending"},outputs:{predicateChange:"predicateChange",ascendingChange:"ascendingChange",sortChange:"sortChange"}}),l})()}}]);