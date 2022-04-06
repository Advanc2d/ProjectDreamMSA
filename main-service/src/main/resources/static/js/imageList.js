// @root start
// @root end

// @slider1 start
(function () {
  var swiper = new Swiper(".slider1 .swiper-container", {
      autoHeight: true,
      loop: true,
      autoplay: {
          delay: 5000,
      },
      navigation: {
          nextEl: ".swiper-button-next",
          prevEl: ".swiper-button-prev",
      },
      pagination: {
          el: ".swiper-pagination",
          clickable: true,
      },
  });
})();
// @slider1 end