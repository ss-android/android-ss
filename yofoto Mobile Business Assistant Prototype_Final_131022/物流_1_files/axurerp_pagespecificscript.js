for(var i = 0; i < 34; i++) { var scriptId = 'u' + i; window[scriptId] = document.getElementById(scriptId); }

$axure.eventManager.pageLoad(
function (e) {

if (true) {

SetWidgetSelected('u30');
}

});
gv_vAlignTable['u31'] = 'center';gv_vAlignTable['u17'] = 'top';document.getElementById('u28_img').tabIndex = 0;

u28.style.cursor = 'pointer';
$axure.eventManager.click('u28', function(e) {

if (true) {

	self.location.href=$axure.globalVariableProvider.getLinkUrl('收货地址_1.html');

}
});
gv_vAlignTable['u29'] = 'center';document.getElementById('u30_img').tabIndex = 0;
HookHover('u30', false);

u30.style.cursor = 'pointer';
$axure.eventManager.click('u30', function(e) {

if (true) {

	SetPanelState('u15', 'pd0u15','none','',500,'none','',500);

SetWidgetSelected('u30');
SetWidgetNotSelected('u32');
}
});
document.getElementById('u21_img').tabIndex = 0;

u21.style.cursor = 'pointer';
$axure.eventManager.click('u21', function(e) {

if (true) {

	self.location.href=$axure.globalVariableProvider.getLinkUrl('选择工作室_1.html');

}
});
document.getElementById('u32_img').tabIndex = 0;
HookHover('u32', false);

u32.style.cursor = 'pointer';
$axure.eventManager.click('u32', function(e) {

if (true) {

	SetPanelState('u15', 'pd1u15','none','',500,'none','',500);

SetWidgetNotSelected('u30');
SetWidgetSelected('u32');
}
});
document.getElementById('u13_img').tabIndex = 0;

u13.style.cursor = 'pointer';
$axure.eventManager.click('u13', function(e) {

if (true) {

	self.location.href=$axure.globalVariableProvider.getLinkUrl('复消零售_1.html');

}
});
gv_vAlignTable['u14'] = 'center';gv_vAlignTable['u1'] = 'center';gv_vAlignTable['u26'] = 'top';gv_vAlignTable['u11'] = 'center';gv_vAlignTable['u3'] = 'center';gv_vAlignTable['u9'] = 'center';gv_vAlignTable['u7'] = 'center';gv_vAlignTable['u24'] = 'top';document.getElementById('u25_img').tabIndex = 0;

u25.style.cursor = 'pointer';
$axure.eventManager.click('u25', function(e) {

if (true) {

	self.location.href=$axure.globalVariableProvider.getLinkUrl('收货地址_1.html');

}
});
document.getElementById('u2_img').tabIndex = 0;
HookHover('u2', false);

u2.style.cursor = 'pointer';
$axure.eventManager.click('u2', function(e) {

if (true) {

	self.location.href=$axure.globalVariableProvider.getLinkUrl('支付方式_1.html');

}
});
document.getElementById('u18_img').tabIndex = 0;

u18.style.cursor = 'pointer';
$axure.eventManager.click('u18', function(e) {

if (true) {

	self.location.href=$axure.globalVariableProvider.getLinkUrl('选择工作室_1.html');

}
});
gv_vAlignTable['u19'] = 'top';gv_vAlignTable['u5'] = 'center';gv_vAlignTable['u22'] = 'center';gv_vAlignTable['u33'] = 'center';