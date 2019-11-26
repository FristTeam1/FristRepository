
var page_example = {

    pageNo:1,

    init:function(){

        for(var i = 0;i < 20;i++) {

            var html = '<div  class="common-items">' +

                '<div class="common-usercontent">' +

                ' 第1页  項目' + i +

                '</div>' +

                '<div class="common-previewcontent">' +

                ' 內容' + i +

                ' </div> ' +

                ' </div>';

            $("#content").append(html);

        }

    },

    loadContent:function(pageNo){

        for(var i = 0;i < 20;i++) {

            var html = '<div  class="common-items">' +

                '<div class="common-usercontent">' +

                ' 第'+pageNo+'页  項目' + i +

                '</div>' +

                '<div class="common-previewcontent">' +

                ' 內容' + i +

                ' </div> ' +

                ' </div>';

            $("#content").append(html);

        }

    }

}

$(function(){

    page_example.init();

    $(window).scroll(function(){

        var mybody = document.body;

        //FF支持document.documentElement.scrollTop，chrome支持document.body.scrollTop

        if(mybody.scrollHeight-mybody.clientHeight <= (mybody.scrollTop | document.documentElement.scrollTop)+5){

            console.log('加载下一页');

            page_example.pageNo++;

            page_example.loadContent( page_example.pageNo)

        }

    });

});
