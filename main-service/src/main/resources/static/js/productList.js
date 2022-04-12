(function () {
  var swiper = new Swiper(".slider .swiper-container", {
      slidesPerView: "auto",
      spaceBetween: 40,
      navigation: {
          nextEl: ".swiper-button-next",
          prevEl: ".swiper-button-prev",
      },
  });
})();