// Set default start and end dates
var startDate = new Date();
startDate.setDate(startDate.getDate() - 7); // 7 days ago
var endDate = new Date();

// Format date to 'YYYY-MM-DD' format
function formatDate(date) {
    var year = date.getFullYear();
    var month = String(date.getMonth() + 1).padStart(2, '0');
    var day = String(date.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
}

// Set default values for start and end date inputs
document.getElementById('startDate').value = formatDate(startDate);
document.getElementById('endDate').value = formatDate(endDate);

// age all button
function toggleAllAges() {
    var allAgeButton = document.getElementById('allAge');
    var ageCheckboxes = document.getElementsByName('ageOptions');
    var isSelected = allAgeButton.classList.contains('active');

    if (!isSelected) {
        allAgeButton.classList.add('active');
        for (var i = 0; i < ageCheckboxes.length; i++) {
            ageCheckboxes[i].checked = true;
        }
    } else {
        allAgeButton.classList.remove('active');
        for (var i = 0; i < ageCheckboxes.length; i++) {
            ageCheckboxes[i].checked = false;
        }
    }
}

// Function to set all age options checked by default
function setAllAgesChecked() {
    var allAgeButton = document.getElementById('allAge');
    var ageCheckboxes = document.getElementsByName('ageOptions');

    // Check all age options
    for (var i = 0; i < ageCheckboxes.length; i++) {
        ageCheckboxes[i].checked = true;
    }

    // Add 'active' class to allAgeButton
    allAgeButton.classList.add('active');
}

// Call setAllAgesChecked function when the page loads
window.onload = function() {
    setAllAgesChecked();
};

// Function to get selected ages
function getSelectedAges() {
    var ageOptions = document.getElementsByName('ageOptions');
    var selectedAges = [];
    for (var i = 0; i < ageOptions.length; i++) {
        if (ageOptions[i].checked) {
            selectedAges.push(ageOptions[i].value);
        }
    }
    return selectedAges.join(','); // Join the selected ages into a comma-separated string
}

// Add event listener to search button
document.getElementById('searchButton').addEventListener('click', function () {
    // Get selected values
    var keyword = document.getElementById('searchInput').value;
    var startDate = document.getElementById('startDate').value;
    var endDate = document.getElementById('endDate').value;
    var categorySelect = document.getElementById('categorySelect');
    var categoryValue = categorySelect.value;
    var category = categorySelect.options[categorySelect.selectedIndex].text;
    var timeunit = document.querySelector('input[name="options"]:checked').value;
    var selectedAges = getSelectedAges(); // Get selected ages

    // Get selected device option
    var deviceOption = document.querySelector('input[name="deviceOptions"]:checked');
    var device = deviceOption.value === 'all' ? "" : deviceOption.value;

    // Get selected gender option
    var genderOption = document.querySelector('input[name="genderOptions"]:checked');
    var gender = genderOption.value === 'all' ? "" : genderOption.value;

    // Perform search with selected values
    console.log('Keyword:', keyword);
    console.log('Start Date:', startDate);
    console.log('End Date:', endDate);
    console.log('Category:', categoryValue);
    console.log('Time Unit:', timeunit);
    console.log('Device:', device);
    console.log('Gender:', gender);
    console.log('Ages:', selectedAges);

    // Create JSON object with the data
    var searchData = {
        startDate: startDate,
        endDate: endDate,
        timeUnit: timeunit,
        category: categoryValue,
        keyword: keyword,
        device: device,
        gender: gender,
        ages: selectedAges.split(',') // Split the selected ages string into an array
    };

    // Perform fetch request to send data to server
    fetch("/shopping-trends", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(searchData)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        console.log("Data sent successfully!");
        // Handle response from server if needed
    })
    .catch(error => {
        console.error('There was a problem with your fetch operation:', error);
    });
});

// Function to get selected option for a given group of radio buttons
function getSelectedOption(groupName) {
    var options = document.getElementsByName(groupName);
    for (var i = 0; i < options.length; i++) {
        if (options[i].checked) {
            return options[i].nextElementSibling.textContent;
        }
    }
    return '전체';
}
