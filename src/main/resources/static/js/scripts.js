
  // SEARCH
  document.addEventListener('DOMContentLoaded', function() {
    var searchForm = document.getElementById('searchForm');
    var searchInput = document.getElementById('searchInput');

    if (searchForm && searchInput) {
      searchForm.addEventListener('submit', function(event) {
        event.preventDefault();
        search();
      });
    }

    function search() {
      var query = searchInput.value.trim();
      if (query !== "") {
        var redirectURL = "/result?q=" + encodeURIComponent(query);
        window.location.href = redirectURL;
      } else {
        console.log("검색어를 입력하세요.");
      }
    }
  });