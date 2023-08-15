const coronaTable = document.getElementById('coronaTable');

// Replace this URL with the API endpoint or your JSON data file
const coronaDataURL = 'https://api.example.com/corona-data';

fetch(coronaDataURL)
    .then(response => response.json())
    .then(data => {
        // Loop through the data and create rows in the table
        data.forEach(countryData => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${countryData.country}</td>
                <td>${countryData.totalCases}</td>
                <td>${countryData.deaths}</td>
                <td>${countryData.recovered}</td>
            `;
            coronaTable.appendChild(row);
        });
    })
    .catch(error => console.error('Error fetching corona data:', error));