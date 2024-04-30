// 로컬 스토리지에서 데이터 가져오기
    var resMessage = localStorage.getItem('resMessage');
    var resData = localStorage.getItem('resData');

// resMessage를 출력하는 요소에 텍스트 설정
    var insightMessageElement = document.getElementById('insightMessage');
    if (resMessage != null) {
        insightMessageElement.innerText = resMessage;
    }

// resData를 가지고 그래프를 그리기 위한 코드
    if (resData != null) {
        var data = JSON.parse(resData);
        var labels = [...new Set(data.results[0].data.map(item => {
            var dateParts = item.period.split('-');
            return dateParts[1] + dateParts[2];
        }))];
        var ageGroups = [...new Set(data.results[0].data.map(item => item.group))]; // 연령대
        var datasets = [];

        ageGroups.forEach(group => {
            var searchDataByGroup = data.results[0].data.filter(item => item.group === group);
            var searchRatios = searchDataByGroup.map(item => item.ratio);

            datasets.push({
                label: group + '대',
                data: searchRatios,
                fill: false,
                borderColor: getColor(datasets.length),
                tension: 0.25
            });
        });

        // 그래프 그리는 함수 호출
        drawChart(labels, datasets);
    }

    function drawChart(labels, datasets) {
        const ctx = document.getElementById('shoppingTrendsChart').getContext('2d');
        ctx.canvas.width = window.innerWidth;
        ctx.canvas.height = window.innerHeight*(4/7);
        var chart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: labels, // 날짜
                datasets: datasets // 검색 비율
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                minHeight: 300,
                scales: {
                    yAxes: [{
                        beginAtZero: true,
                        reverse: true,
                        min: 0,
                        max: 100,
                        ticks: {
                            stepSize : 25,
                            font: {
                                weight: 'bold'
                            },
                            fontSize : 14
                        }
                    }],
                    xAxes: [{
                        ticks: {
                            font: {
                                weight: 'bold'
                            },
                            fontSize : 14
                        }
                    }]
                },
                elements: {
                    line: {
                        borderWidth: 1.5
                    }
                }
            }
        });
    }

    // 색상
    function getColor(index) {
        var colors = ['#FA5858', '#01DFD7', '#2E64FE', '#DF01A5', '#D7DF01', '#01DF01'];
        return colors[index % colors.length];
    }