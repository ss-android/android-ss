for(var i = 0; i < 21; i++) { var scriptId = 'u' + i; window[scriptId] = document.getElementById(scriptId); }

$axure.eventManager.pageLoad(
function (e) {

});
gv_vAlignTable['u3'] = 'center';gv_vAlignTable['u15'] = 'center';
u19.style.cursor = 'pointer';
$axure.eventManager.click('u19', function(e) {

if ((GetCheckState('u19')) == (true)) {

	SetPanelVisibility('u8','','none',500);

}
});
document.getElementById('u10_img').tabIndex = 0;

u10.style.cursor = 'pointer';
$axure.eventManager.click('u10', function(e) {

if (true) {

	self.location.href=$axure.globalVariableProvider.getLinkUrl('在线购物_2.html');

}
});

u17.style.cursor = 'pointer';
$axure.eventManager.click('u17', function(e) {

if ((GetCheckState('u17')) == (true)) {

	SetPanelVisibility('u8','hidden','none',500);

}
});
gv_vAlignTable['u5'] = 'center';gv_vAlignTable['u1'] = 'center';gv_vAlignTable['u20'] = 'top';document.getElementById('u6_img').tabIndex = 0;

u6.style.cursor = 'pointer';
$axure.eventManager.click('u6', function(e) {

if (true) {

	self.location.href=$axure.globalVariableProvider.getLinkUrl('工作室主页.html');

}
});
gv_vAlignTable['u11'] = 'center';gv_vAlignTable['u13'] = 'center';gv_vAlignTable['u18'] = 'top';gv_vAlignTable['u7'] = 'center';