//@author: Sam Li
//Vertical tab for admin tool
// As a helper to generate left navigator.
var VerticalTab = function(tabID, contentID, tabPath, tabClassName, options){
    this._tabID = tabID;
    this._contentID = contentID;
    this._tabPath = tabPath;
    this._tabClassName = tabClassName;
    this._options = options;
    this._currentTabIndex = 0;
    this._totalTabs = 0;
    this._contentDIVNamePrefix = contentID+ "-tab-content-div-";
    this._tabNamePrefix = contentID + "vertical-tab-";
}
VerticalTab.CONTENT_DIV_NANE_PREFIX = "vertical-tab-content-div-";
VerticalTab.TAB_NAME_PREFIX = "vertical-tab-";
VerticalTab.prototype.drawTabs = function(){
    var verticalTab = this;
    $.each($("#" + this._tabID + this._tabPath), function(i, e){
        //alert($(e).parent().attr("name"));
        verticalTab.createContent(i);
        verticalTab.maintainTab(i, e);
        verticalTab._totalTabs++;
    });
	
    // defult select first <a>.
    this.initSelect();
}

VerticalTab.prototype.initSelect = function() {
    if (this._options && this._options.selected && this._totalTabs > this._options.selected && 0 <= this._options.selected) {
        $("#" + this._tabNamePrefix + this._options.selected).click();
    } else {
        $("#" + this._tabNamePrefix + this._currentTabIndex).click();
    }
}


VerticalTab.prototype.createContent = function(index){
    var innerHTML = $("#" + this._contentID).html();
    innerHTML += '<div id="' + this._contentDIVNamePrefix + index + '" class="content_'+ this._contentID +' ui-tabs-hide" ></div>';
    $("#" + this._contentID).html(innerHTML);
}

VerticalTab.prototype.maintainTab = function(index, element){
    var verticalTab = this;
    var a = $(element);
    // replace #vertical-tab-n to a.href
    var href = a.attr("href");
    a.attr("id", this._tabNamePrefix + index);
    a.attr("href", "#" + this._contentDIVNamePrefix + index);
    // use a.href to bind onclick function to <a>( load url, add class)
    a.click(function(){
        $.each($(".content_" + verticalTab._contentID), function(i, e){
            $(e).empty();
        });
        $("#" + verticalTab._tabNamePrefix + verticalTab._currentTabIndex).removeClass(verticalTab._tabClassName);
        $("#" +verticalTab. _contentDIVNamePrefix + verticalTab._currentTabIndex).addClass("ui-tabs-hide");
        $("#" + verticalTab._contentDIVNamePrefix + index).load(href);
        verticalTab._currentTabIndex = index;
        a.addClass(verticalTab._tabClassName);
        $("#" + verticalTab._contentDIVNamePrefix + index).removeClass("ui-tabs-hide");
        $("#tab_title").text(a.text());
    });
    // add mouseouver mouseout function to <a>
    a.mouseover(function(){
        a.addClass("selected");
    });
    a.mouseout(function(){
        if (verticalTab._currentTabIndex != index){
            a.removeClass("selected");
        }
    })
}

// demo
//$(function(){
//	var verticalTab  = new VerticalTab("sidebarmenu_2_1", "m_2_c_1_tabs");
//	verticalTab.drawTabs();
//});

var VerticalFlexTab = function(tabID, contentID, options){
    VerticalTab.call(this, tabID, contentID, options);
}
VerticalFlexTab.prototype = new VerticalTab();
VerticalFlexTab.CONTENT_DIV_NANE_PREFIX = "vertical-flex-tab-content-div-";
VerticalFlexTab.TAB_NAME_PREFIX = "vertical-flex-tab-";
VerticalFlexTab.TAB_DIV_NAME_PREFIX = "vertical-sub-flex-tab-";
VerticalFlexTab.prototype.drawTabs = function(){
    var verticalFlexTab = this;
    var index = 0;
    $.each($("#" + this._tabID + " li > div"), function(i, e){
        //alert($(e).parent().attr("name"));
        // do flex
        if (i!=0) {
            $(e).addClass("vertical-flex-tab-div");
        }
        $(e).attr("id", VerticalFlexTab.TAB_DIV_NAME_PREFIX + i);
        $(e).prev().addClass("vertical-flex-parent-tab");
        $(e).prev().click(function(){
            if($(e).is(":hidden")){
                if(!$(e).is(":animated")){
                    $(e).animate({
                        height:'show'
                    },1000);
                    $(e).parent().siblings().find("div").hide(1000);
                    verticalFlexTab._currentOpenTabIndex = i;
                }
            }else{
                if(!$(e).is(":animated")){
                    $(e).animate({
                        height:'hide'
                    },1000);
                }
            }
        });
        // maintain tab.
        $.each($(e).find("a"), function(n,el){
            verticalFlexTab.createContent(index);
            verticalFlexTab.maintainTab(index, el);
            verticalFlexTab._totalTabs++;
            index++;
        });
    });
    this.initSelect();
}
//demo
//$(function(){
//	var verticalTab  = new VerticalFlexTab("reportLeftNavigator", "reportContent", {selected: -1});
//	verticalTab.drawTabs();
//});