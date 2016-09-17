/**
 * 
 */

$(window).on('load', function () {
    var $preloader = $('#loader-wrapper'),
        $spinner   = $preloader.find('#loader');
    console.log($preloader);
    console.log($spinner);
    $spinner.fadeOut();
    $preloader.delay(350).fadeOut('slow');
});