readXML("Y");
$("#X").click(function () {
    var letterX = "X";
    readXML(letterX);
});
$("#Y").click(function () {
    var letterY = "Y";
    readXML(letterY);
});
$("#Z").click(function () {
    var letterZ = "Z";
    readXML(letterZ);
});
$("#DK").click(function() {
    var letterDK = "K";
    readXML(letterDK);
});
function readXML(letterClass) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            showData(this, letterClass);
        }
    };
    xhttp.open("GET", "timetable.xml", true);
    xhttp.send();
}
function getWeekNumber(d) {
    // Copy date so don't modify original
    d = new Date(Date.UTC(d.getFullYear(), d.getMonth(), d.getDate()));
    // Set to nearest Thursday: current date + 4 - current day number
    // Make Sunday's day number 7
    d.setUTCDate(d.getUTCDate() + 4 - (d.getUTCDay() || 7));
    // Get first day of year
    var yearStart = new Date(Date.UTC(d.getUTCFullYear(), 0, 1));
    // Calculate full weeks to nearest Thursday
    var weekNo = Math.ceil((((d - yearStart) / 86400000) + 1) / 7);
    // Return array of year and week number
    return [d.getUTCFullYear(), weekNo];
}
function showData(xml, letter) {
    var xmlDoc = xml.responseXML;
    var s = xmlDoc.getElementsByTagName("sessionList");
    resultHere = getWeekNumber(new Date(2022, 0, 24));
    var table = "<thead><th scope='col' class='table-light'> Week " + resultHere[1] + " </th><th scope='col' class='table-light'> DMA1 </th><th scope='col' class='table-light'> RWD1 </th><th scope='col' class='table-light'> SDJ1 </th><th scope='col' class='table-light'> SEP1 </th></thead> <tbody>";
    var monday = "<tr id='monday'> <th scope='row'>Monday</th>";
    var tuesday = " <tr id='tuesday'> <th scope='row'>Tuesday</th>";
    var wednesday = " <tr id='wednesday'> <th scope='row'>Wednesday</th>";
    var thursday = " <tr id='thursday'> <th scope='row'>Thursday</th>";
    var friday = " <tr id='friday'> <th scope='row'>Friday</th>";

    for (i = 0; i < s.length; i++) {
        var courseID = s[i].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
        var classLetter = courseID.substr(1, 1);
        if (letter == classLetter) {
            var dString = s[i].getElementsByTagName("year")[0].childNodes[0].nodeValue + "," + s[i].getElementsByTagName("month")[0].childNodes[0].nodeValue + "," + s[i].getElementsByTagName("day")[0].childNodes[0].nodeValue;
            const d = new Date(dString);
            var result = getWeekNumber(d);

            if (resultHere[1] == result[1]) {
                var dString = s[i].getElementsByTagName("year")[0].childNodes[0].nodeValue + "," + s[i].getElementsByTagName("month")[0].childNodes[0].nodeValue + "," + s[i].getElementsByTagName("day")[0].childNodes[0].nodeValue;
                const d = new Date(dString);
                let weekday = d.getDay();
                var teacherID = s[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                var startTime = s[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[0].childNodes[0].nodeValue;
                var endTime = s[i].getElementsByTagName("hour")[1].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[1].childNodes[0].nodeValue;
                var courseID = s[i].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                var session = courseID.substr(2, 3);
                var room = "";
                if (s[i].getElementsByTagName("room").length !== 0) {
                    room = s[i].getElementsByTagName("room")[0].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                    if (s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith").length !== 0)
                        room = room + " + " + s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith")[0].childNodes[0].nodeValue;
                }

                switch (weekday) {
                    case 1:
                        if (session === "DMA") {
                            monday += "<td>" + startTime + "-" + endTime + "<br>" + teacherID + "<br>" + room + "</td>";
                            room = "";
                            if (i < s.length - 1) {
                                var dString = s[i + 1].getElementsByTagName("year")[0].childNodes[0].nodeValue + "," + s[i + 1].getElementsByTagName("month")[0].childNodes[0].nodeValue + "," + s[i + 1].getElementsByTagName("day")[0].childNodes[0].nodeValue;
                                const d = new Date(dString);
                                let weekday = d.getDay();
                                var courseID = s[i+1].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                var classLetter = courseID.substr(1, 1);
                                while(letter != classLetter && i<s.length-1)
                                {
                                    i++;
                                    var courseID = s[i+1].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                    var classLetter = courseID.substr(1, 1);
                                }
                                if (weekday == 1) {
                                    i++;
                                    var teacherID = s[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                                    var startTime = s[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[0].childNodes[0].nodeValue;
                                    var endTime = s[i].getElementsByTagName("hour")[1].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[1].childNodes[0].nodeValue;
                                    var courseID = s[i].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                    var session = courseID.substr(2, 3);
                                    if (s[i].getElementsByTagName("room").length !== 0) {
                                        room = s[i].getElementsByTagName("room")[0].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                                        if (s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith").length !== 0)
                                            room = room + " + " + s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith")[0].childNodes[0].nodeValue;
                                    }
                                }
                            }
                        }
                        else if (session !== "DMA")
                            monday += "<td></td>";
                        if (session === "RWD") {
                            monday += "<td>" + startTime + "-" + endTime + "<br>" + teacherID + "<br>" + room + "</td>";
                            room = "";
                            if (i < s.length - 1) {
                                var dString = s[i + 1].getElementsByTagName("year")[0].childNodes[0].nodeValue + "," + s[i + 1].getElementsByTagName("month")[0].childNodes[0].nodeValue + "," + s[i + 1].getElementsByTagName("day")[0].childNodes[0].nodeValue;
                                const d = new Date(dString);
                                let weekday = d.getDay();
                                var courseID = s[i+1].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                var classLetter = courseID.substr(1, 1);
                                while(letter != classLetter && i<s.length-1)
                                {
                                    i++;
                                    var courseID = s[i+1].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                    var classLetter = courseID.substr(1, 1);
                                }
                                if (weekday == 1) {
                                    i++;
                                    var teacherID = s[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                                    var startTime = s[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[0].childNodes[0].nodeValue;
                                    var endTime = s[i].getElementsByTagName("hour")[1].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[1].childNodes[0].nodeValue;
                                    var courseID = s[i].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                    var session = courseID.substr(2, 3);
                                    if (s[i].getElementsByTagName("room").length !== 0) {
                                        room = s[i].getElementsByTagName("room")[0].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                                        if (s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith").length !== 0)
                                            room = room + " + " + s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith")[0].childNodes[0].nodeValue;
                                    }
                                }
                            }
                        }
                        else if (session !== "RWD")
                            monday += "<td></td>";
                        if (session === "SDJ") {
                            monday += "<td>" + startTime + "-" + endTime + "<br>" + teacherID + "<br>" + room + "</td>";
                            room = "";
                            if (i < s.length - 1) {
                                var dString = s[i + 1].getElementsByTagName("year")[0].childNodes[0].nodeValue + "," + s[i + 1].getElementsByTagName("month")[0].childNodes[0].nodeValue + "," + s[i + 1].getElementsByTagName("day")[0].childNodes[0].nodeValue;
                                const d = new Date(dString);
                                let weekday = d.getDay();
                                var courseID = s[i+1].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                var classLetter = courseID.substr(1, 1);
                                while(letter != classLetter && i<s.length-1)
                                {
                                    i++;
                                    var courseID = s[i+1].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                    var classLetter = courseID.substr(1, 1);
                                }
                                if (weekday == 1) {
                                    i++;
                                    var teacherID = s[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                                    var startTime = s[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[0].childNodes[0].nodeValue;
                                    var endTime = s[i].getElementsByTagName("hour")[1].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[1].childNodes[0].nodeValue;
                                    var courseID = s[i].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                    var session = courseID.substr(2, 3);
                                    if (s[i].getElementsByTagName("room").length !== 0) {
                                        room = s[i].getElementsByTagName("room")[0].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                                        if (s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith").length !== 0)
                                            room = room + " + " + s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith")[0].childNodes[0].nodeValue;
                                    }
                                }
                            }
                        }
                        else if (session !== "SDJ")
                            monday += "<td></td>";
                        if (session === "SEP") {
                            monday += "<td>" + startTime + "-" + endTime + "<br>" + teacherID + "<br>" + room + "</td>";
                            room = "";
                            if (i < s.length - 1) {
                                var dString = s[i + 1].getElementsByTagName("year")[0].childNodes[0].nodeValue + "," + s[i + 1].getElementsByTagName("month")[0].childNodes[0].nodeValue + "," + s[i + 1].getElementsByTagName("day")[0].childNodes[0].nodeValue;
                                const d = new Date(dString);
                                let weekday = d.getDay();
                                var courseID = s[i+1].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                var classLetter = courseID.substr(1, 1);
                                while(letter != classLetter && i<s.length-1)
                               {
                                   i++;
                                   var courseID = s[i+1].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                   var classLetter = courseID.substr(1, 1);
                               }
                               if (weekday == 1) {
                                    i++;
                                    var teacherID = s[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                                    var startTime = s[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[0].childNodes[0].nodeValue;
                                    var endTime = s[i].getElementsByTagName("hour")[1].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[1].childNodes[0].nodeValue;
                                    var courseID = s[i].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                    var session = courseID.substr(2, 3);
                                    if (s[i].getElementsByTagName("room").length !== 0) {
                                        room = s[i].getElementsByTagName("room")[0].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                                        if (s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith").length !== 0)
                                            room = room + " + " + s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith")[0].childNodes[0].nodeValue;
                                    }
                                }
                            }
                        }
                        else if (session !== "SEP")
                            monday += "<td></td>";
                        monday += "</tr>";
                        break;
                    case 2:
                        if (session === "DMA") {
                            tuesday += "<td>" + startTime + "-" + endTime + "<br>" + teacherID + "<br>" + room + "</td>";
                            room = "";
                            if (i < s.length - 1) {
                                var dString = s[i + 1].getElementsByTagName("year")[0].childNodes[0].nodeValue + "," + s[i + 1].getElementsByTagName("month")[0].childNodes[0].nodeValue + "," + s[i + 1].getElementsByTagName("day")[0].childNodes[0].nodeValue;
                                const d = new Date(dString);
                                let weekday = d.getDay();
                                var courseID = s[i+1].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                var classLetter = courseID.substr(1, 1);
                                while(letter != classLetter && i<s.length-1)
                                {
                                    i++;
                                    var courseID = s[i+1].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                    var classLetter = courseID.substr(1, 1);
                                }
                                if (weekday == 2) {
                                    i++;
                                    var teacherID = s[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                                    var startTime = s[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[0].childNodes[0].nodeValue;
                                    var endTime = s[i].getElementsByTagName("hour")[1].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[1].childNodes[0].nodeValue;
                                    var courseID = s[i].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                    var session = courseID.substr(2, 3);
                                    if (s[i].getElementsByTagName("room").length !== 0) {
                                        room = s[i].getElementsByTagName("room")[0].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                                        if (s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith").length !== 0)
                                            room = room + " + " + s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith")[0].childNodes[0].nodeValue;
                                    }
                                }
                            }
                        }
                        else if (session !== "DMA")
                            tuesday += "<td></td>";
                        if (session === "RWD") {
                            tuesday += "<td>" + startTime + "-" + endTime + "<br>" + teacherID + "<br>" + room + "</td>";
                            room = "";
                            if (i < s.length - 1) {
                                var dString = s[i + 1].getElementsByTagName("year")[0].childNodes[0].nodeValue + "," + s[i + 1].getElementsByTagName("month")[0].childNodes[0].nodeValue + "," + s[i + 1].getElementsByTagName("day")[0].childNodes[0].nodeValue;
                                const d = new Date(dString);
                                let weekday = d.getDay();
                                var courseID = s[i+1].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                var classLetter = courseID.substr(1, 1);
                                while(letter != classLetter && i<s.length-1)
                                {
                                    i++;
                                    var courseID = s[i+1].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                    var classLetter = courseID.substr(1, 1);
                                }
                                if (weekday == 2) {
                                    i++;
                                    var teacherID = s[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                                    var startTime = s[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[0].childNodes[0].nodeValue;
                                    var endTime = s[i].getElementsByTagName("hour")[1].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[1].childNodes[0].nodeValue;
                                    var courseID = s[i].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                    var session = courseID.substr(2, 3);
                                    if (s[i].getElementsByTagName("room").length !== 0) {
                                        room = s[i].getElementsByTagName("room")[0].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                                        if (s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith").length !== 0)
                                            room = room + " + " + s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith")[0].childNodes[0].nodeValue;
                                    }
                                }
                            }
                        }
                        else if (session !== "RWD")
                            tuesday += "<td></td>";
                        if (session === "SDJ") {
                            tuesday += "<td>" + startTime + "-" + endTime + "<br>" + teacherID + "<br>" + room + "</td>";
                            room = "";
                            if (i < s.length - 1) {
                                var dString = s[i + 1].getElementsByTagName("year")[0].childNodes[0].nodeValue + "," + s[i + 1].getElementsByTagName("month")[0].childNodes[0].nodeValue + "," + s[i + 1].getElementsByTagName("day")[0].childNodes[0].nodeValue;
                                const d = new Date(dString);
                                let weekday = d.getDay();
                                var courseID = s[i+1].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                var classLetter = courseID.substr(1, 1);
                                while(letter != classLetter && i<s.length-1)
                                {
                                    i++;
                                    var courseID = s[i+1].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                    var classLetter = courseID.substr(1, 1);
                                }
                                if (weekday == 2) {
                                    i++;
                                    var teacherID = s[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                                    var startTime = s[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[0].childNodes[0].nodeValue;
                                    var endTime = s[i].getElementsByTagName("hour")[1].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[1].childNodes[0].nodeValue;
                                    var courseID = s[i].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                    var session = courseID.substr(2, 3);
                                    if (s[i].getElementsByTagName("room").length !== 0) {
                                        room = s[i].getElementsByTagName("room")[0].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                                        if (s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith").length !== 0)
                                            room = room + " + " + s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith")[0].childNodes[0].nodeValue;
                                    }
                                }
                            }
                        }
                        else if (session !== "SDJ")
                            tuesday += "<td></td>";
                        if (session === "SEP") {
                            tuesday += "<td>" + startTime + "-" + endTime + "<br>" + teacherID + "<br>" + room + "</td>";
                            room = "";
                            if (i < s.length - 1) {
                                var dString = s[i + 1].getElementsByTagName("year")[0].childNodes[0].nodeValue + "," + s[i + 1].getElementsByTagName("month")[0].childNodes[0].nodeValue + "," + s[i + 1].getElementsByTagName("day")[0].childNodes[0].nodeValue;
                                const d = new Date(dString);
                                let weekday = d.getDay();
                                var courseID = s[i+1].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                var classLetter = courseID.substr(1, 1);
                                while(letter != classLetter && i<s.length-1)
                                {
                                    i++;
                                    var courseID = s[i+1].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                    var classLetter = courseID.substr(1, 1);
                                }
                                if (weekday == 2) {
                                    i++;
                                    var teacherID = s[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                                    var startTime = s[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[0].childNodes[0].nodeValue;
                                    var endTime = s[i].getElementsByTagName("hour")[1].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[1].childNodes[0].nodeValue;
                                    var courseID = s[i].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                    var session = courseID.substr(2, 3);
                                    if (s[i].getElementsByTagName("room").length !== 0) {
                                        room = s[i].getElementsByTagName("room")[0].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                                        if (s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith").length !== 0)
                                            room = room + " + " + s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith")[0].childNodes[0].nodeValue;
                                    }
                                }
                            }
                        }
                        else if (session !== "SEP")
                            tuesday += "<td></td>";
                        tuesday += "</tr>";
                        break;
                    case 3:
                        if (session === "DMA") {
                            wednesday += "<td>" + startTime + "-" + endTime + "<br>" + teacherID + "<br>" + room + "</td>";
                            room = "";
                            if (i < s.length - 1) {
                                var dString = s[i + 1].getElementsByTagName("year")[0].childNodes[0].nodeValue + "," + s[i + 1].getElementsByTagName("month")[0].childNodes[0].nodeValue + "," + s[i + 1].getElementsByTagName("day")[0].childNodes[0].nodeValue;
                                const d = new Date(dString);
                                let weekday = d.getDay();
                                var courseID = s[i+1].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                var classLetter = courseID.substr(1, 1);
                                while(letter != classLetter && i<s.length-1)
                                {
                                    i++;
                                    var courseID = s[i+1].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                    var classLetter = courseID.substr(1, 1);
                                }
                                if (weekday == 3) {
                                    i++;
                                    var teacherID = s[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                                    var startTime = s[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[0].childNodes[0].nodeValue;
                                    var endTime = s[i].getElementsByTagName("hour")[1].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[1].childNodes[0].nodeValue;
                                    var courseID = s[i].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                    var session = courseID.substr(2, 3);
                                    if (s[i].getElementsByTagName("room").length !== 0) {
                                        room = s[i].getElementsByTagName("room")[0].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                                        if (s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith").length !== 0)
                                            room = room + " + " + s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith")[0].childNodes[0].nodeValue;
                                    }
                                }
                            }
                        }
                        else if (session !== "DMA")
                            wednesday += "<td></td>";
                        if (session === "RWD") {
                            wednesday += "<td>" + startTime + "-" + endTime + "<br>" + teacherID + "<br>" + room + "</td>";
                            room = "";
                            if (i < s.length - 1) {
                                var dString = s[i + 1].getElementsByTagName("year")[0].childNodes[0].nodeValue + "," + s[i + 1].getElementsByTagName("month")[0].childNodes[0].nodeValue + "," + s[i + 1].getElementsByTagName("day")[0].childNodes[0].nodeValue;
                                const d = new Date(dString);
                                let weekday = d.getDay();
                                var courseID = s[i+1].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                var classLetter = courseID.substr(1, 1);
                                while(letter != classLetter && i<s.length-1)
                                {
                                    i++;
                                    var courseID = s[i+1].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                    var classLetter = courseID.substr(1, 1);
                                }
                                if (weekday == 3) {
                                    i++;
                                    var teacherID = s[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                                    var startTime = s[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[0].childNodes[0].nodeValue;
                                    var endTime = s[i].getElementsByTagName("hour")[1].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[1].childNodes[0].nodeValue;
                                    var courseID = s[i].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                    var session = courseID.substr(2, 3);
                                    if (s[i].getElementsByTagName("room").length !== 0) {
                                        room = s[i].getElementsByTagName("room")[0].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                                        if (s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith").length !== 0)
                                            room = room + " + " + s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith")[0].childNodes[0].nodeValue;
                                    }
                                }
                            }
                        }
                        else if (session !== "RWD")
                            wednesday += "<td></td>";
                        if (session === "SDJ") {
                            wednesday += "<td>" + startTime + "-" + endTime + "<br>" + teacherID + "<br>" + room + "</td>";
                            room = "";
                            if (i < s.length - 1) {
                                var dString = s[i + 1].getElementsByTagName("year")[0].childNodes[0].nodeValue + "," + s[i + 1].getElementsByTagName("month")[0].childNodes[0].nodeValue + "," + s[i + 1].getElementsByTagName("day")[0].childNodes[0].nodeValue;
                                const d = new Date(dString);
                                let weekday = d.getDay();
                                var courseID = s[i+1].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                var classLetter = courseID.substr(1, 1);
                                while(letter != classLetter && i<s.length-1)
                                {
                                    i++;
                                    var courseID = s[i+1].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                    var classLetter = courseID.substr(1, 1);
                                }
                                if (weekday == 3) {
                                    i++;
                                    var teacherID = s[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                                    var startTime = s[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[0].childNodes[0].nodeValue;
                                    var endTime = s[i].getElementsByTagName("hour")[1].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[1].childNodes[0].nodeValue;
                                    var courseID = s[i].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                    var session = courseID.substr(2, 3);
                                    if (s[i].getElementsByTagName("room").length !== 0) {
                                        room = s[i].getElementsByTagName("room")[0].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                                        if (s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith").length !== 0)
                                            room = room + " + " + s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith")[0].childNodes[0].nodeValue;
                                    }
                                }
                            }
                        }
                        else if (session !== "SDJ")
                            wednesday += "<td></td>";
                        if (session === "SEP") {
                            wednesday += "<td>" + startTime + "-" + endTime + "<br>" + teacherID + "<br>" + room + "</td>";
                            room = "";
                            if (i < s.length - 1) {
                                var dString = s[i + 1].getElementsByTagName("year")[0].childNodes[0].nodeValue + "," + s[i + 1].getElementsByTagName("month")[0].childNodes[0].nodeValue + "," + s[i + 1].getElementsByTagName("day")[0].childNodes[0].nodeValue;
                                const d = new Date(dString);
                                let weekday = d.getDay();
                                var courseID = s[i+1].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                var classLetter = courseID.substr(1, 1);
                                while(letter != classLetter && i<s.length-1)
                                {
                                    i++;
                                    var courseID = s[i+1].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                    var classLetter = courseID.substr(1, 1);
                                }
                                if (weekday == 3) {
                                    i++;
                                    var teacherID = s[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                                    var startTime = s[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[0].childNodes[0].nodeValue;
                                    var endTime = s[i].getElementsByTagName("hour")[1].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[1].childNodes[0].nodeValue;
                                    var courseID = s[i].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                    var session = courseID.substr(2, 3);
                                    if (s[i].getElementsByTagName("room").length !== 0) {
                                        room = s[i].getElementsByTagName("room")[0].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                                        if (s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith").length !== 0)
                                            room = room + " + " + s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith")[0].childNodes[0].nodeValue;
                                    }
                                }
                            }
                        }
                        else if (session !== "SEP")
                            wednesday += "<td></td>";
                        wednesday += "</tr>";
                        break;
                    case 4:
                        if (session === "DMA") {
                            thursday += "<td>" + startTime + "-" + endTime + "<br>" + teacherID + "<br>" + room + "</td>";
                            room = "";
                            if (i < s.length - 1) {
                                var dString = s[i + 1].getElementsByTagName("year")[0].childNodes[0].nodeValue + "," + s[i + 1].getElementsByTagName("month")[0].childNodes[0].nodeValue + "," + s[i + 1].getElementsByTagName("day")[0].childNodes[0].nodeValue;
                                const d = new Date(dString);
                                let weekday = d.getDay();
                                var courseID = s[i+1].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                var classLetter = courseID.substr(1, 1);
                                while(letter != classLetter && i<s.length-1)
                                {
                                    i++;
                                    var courseID = s[i+1].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                    var classLetter = courseID.substr(1, 1);
                                }
                                if (weekday == 4) {
                                    i++;
                                    var teacherID = s[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                                    var startTime = s[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[0].childNodes[0].nodeValue;
                                    var endTime = s[i].getElementsByTagName("hour")[1].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[1].childNodes[0].nodeValue;
                                    var courseID = s[i].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                    var session = courseID.substr(2, 3);
                                    if (s[i].getElementsByTagName("room").length !== 0) {
                                        room = s[i].getElementsByTagName("room")[0].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                                        if (s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith").length !== 0)
                                            room = room + " + " + s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith")[0].childNodes[0].nodeValue;
                                    }
                                }
                            }
                        }
                        else if (session !== "DMA")
                            thursday += "<td></td>";
                        if (session === "RWD") {
                            thursday += "<td>" + startTime + "-" + endTime + "<br>" + teacherID + "<br>" + room + "</td>";
                            room = "";
                            if (i < s.length - 1) {
                                var dString = s[i + 1].getElementsByTagName("year")[0].childNodes[0].nodeValue + "," + s[i + 1].getElementsByTagName("month")[0].childNodes[0].nodeValue + "," + s[i + 1].getElementsByTagName("day")[0].childNodes[0].nodeValue;
                                const d = new Date(dString);
                                let weekday = d.getDay();
                                var courseID = s[i+1].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                var classLetter = courseID.substr(1, 1);
                                while(letter != classLetter && i<s.length-1)
                                {
                                    i++;
                                    var courseID = s[i+1].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                    var classLetter = courseID.substr(1, 1);
                                }
                                if (weekday == 4) {
                                    i++;
                                    var teacherID = s[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                                    var startTime = s[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[0].childNodes[0].nodeValue;
                                    var endTime = s[i].getElementsByTagName("hour")[1].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[1].childNodes[0].nodeValue;
                                    var courseID = s[i].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                    var session = courseID.substr(2, 3);
                                    if (s[i].getElementsByTagName("room").length !== 0) {
                                        room = s[i].getElementsByTagName("room")[0].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                                        if (s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith").length !== 0)
                                            room = room + " + " + s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith")[0].childNodes[0].nodeValue;
                                    }
                                }
                            }
                        }
                        else if (session !== "RWD")
                            thursday += "<td></td>";
                        if (session === "SDJ") {
                            thursday += "<td>" + startTime + "-" + endTime + "<br>" + teacherID + "<br>" + room + "</td>";
                            room = "";
                            if (i < s.length - 1) {
                                var dString = s[i + 1].getElementsByTagName("year")[0].childNodes[0].nodeValue + "," + s[i + 1].getElementsByTagName("month")[0].childNodes[0].nodeValue + "," + s[i + 1].getElementsByTagName("day")[0].childNodes[0].nodeValue;
                                const d = new Date(dString);
                                let weekday = d.getDay();
                                var courseID = s[i+1].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                var classLetter = courseID.substr(1, 1);
                                while(letter != classLetter && i<s.length-1)
                                {
                                    i++;
                                    var courseID = s[i+1].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                    var classLetter = courseID.substr(1, 1);
                                }
                                if (weekday == 4) {
                                    i++;
                                    var teacherID = s[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                                    var startTime = s[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[0].childNodes[0].nodeValue;
                                    var endTime = s[i].getElementsByTagName("hour")[1].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[1].childNodes[0].nodeValue;
                                    var courseID = s[i].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                    var session = courseID.substr(2, 3);
                                    if (s[i].getElementsByTagName("room").length !== 0) {
                                        room = s[i].getElementsByTagName("room")[0].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                                        if (s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith").length !== 0)
                                            room = room + " + " + s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith")[0].childNodes[0].nodeValue;
                                    }
                                }
                            }
                        }
                        else if (session !== "SDJ")
                            thursday += "<td></td>";
                        if (session === "SEP") {
                            thursday += "<td>" + startTime + "-" + endTime + "<br>" + teacherID + "<br>" + room + "</td>";
                            room = "";
                            if (i < s.length - 1) {
                                var dString = s[i + 1].getElementsByTagName("year")[0].childNodes[0].nodeValue + "," + s[i + 1].getElementsByTagName("month")[0].childNodes[0].nodeValue + "," + s[i + 1].getElementsByTagName("day")[0].childNodes[0].nodeValue;
                                const d = new Date(dString);
                                let weekday = d.getDay();
                                var courseID = s[i+1].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                var classLetter = courseID.substr(1, 1);
                                while(letter != classLetter && i<s.length-1)
                                {
                                    i++;
                                    var courseID = s[i+1].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                    var classLetter = courseID.substr(1, 1);
                                }
                                if (weekday == 4) {
                                    i++;
                                    var teacherID = s[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                                    var startTime = s[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[0].childNodes[0].nodeValue;
                                    var endTime = s[i].getElementsByTagName("hour")[1].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[1].childNodes[0].nodeValue;
                                    var courseID = s[i].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                    var session = courseID.substr(2, 3);
                                    if (s[i].getElementsByTagName("room").length !== 0) {
                                        room = s[i].getElementsByTagName("room")[0].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                                        if (s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith").length !== 0)
                                            room = room + " + " + s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith")[0].childNodes[0].nodeValue;
                                    }
                                }
                            }
                        }
                        else if (session !== "SEP")
                            thursday += "<td></td>";
                        thursday += "</tr>";
                        break;
                    case 5:
                        if (session === "DMA") {
                            friday += "<td>" + startTime + "-" + endTime + "<br>" + teacherID + "<br>" + room + "</td>";
                            room = "";
                            if (i < s.length - 1) {
                                var dString = s[i + 1].getElementsByTagName("year")[0].childNodes[0].nodeValue + "," + s[i + 1].getElementsByTagName("month")[0].childNodes[0].nodeValue + "," + s[i + 1].getElementsByTagName("day")[0].childNodes[0].nodeValue;
                                const d = new Date(dString);
                                let weekday = d.getDay();
                                var courseID = s[i+1].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                var classLetter = courseID.substr(1, 1);
                                while(letter != classLetter && i<s.length-1)
                                {
                                    i++;
                                    var courseID = s[i+1].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                    var classLetter = courseID.substr(1, 1);
                                }
                                if (weekday == 5) {
                                    i++;
                                    var teacherID = s[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                                    var startTime = s[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[0].childNodes[0].nodeValue;
                                    var endTime = s[i].getElementsByTagName("hour")[1].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[1].childNodes[0].nodeValue;
                                    var courseID = s[i].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                    var session = courseID.substr(2, 3);
                                    if (s[i].getElementsByTagName("room").length !== 0) {
                                        room = s[i].getElementsByTagName("room")[0].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                                        if (s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith").length !== 0)
                                            room = room + " + " + s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith")[0].childNodes[0].nodeValue;
                                    }
                                }
                            }
                        }
                        else if (session !== "DMA")
                            friday += "<td></td>";
                        if (session === "RWD") {
                            friday += "<td>" + startTime + "-" + endTime + "<br>" + teacherID + "<br>" + room + "</td>";
                            room = "";
                            if (i < s.length - 1) {
                                var dString = s[i + 1].getElementsByTagName("year")[0].childNodes[0].nodeValue + "," + s[i + 1].getElementsByTagName("month")[0].childNodes[0].nodeValue + "," + s[i + 1].getElementsByTagName("day")[0].childNodes[0].nodeValue;
                                const d = new Date(dString);
                                let weekday = d.getDay();
                                var courseID = s[i+1].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                var classLetter = courseID.substr(1, 1);
                                while(letter != classLetter && i<s.length-1)
                                {
                                    i++;
                                    var courseID = s[i+1].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                    var classLetter = courseID.substr(1, 1);
                                }
                                if (weekday == 5) {
                                    i++;
                                    var teacherID = s[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                                    var startTime = s[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[0].childNodes[0].nodeValue;
                                    var endTime = s[i].getElementsByTagName("hour")[1].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[1].childNodes[0].nodeValue;
                                    var courseID = s[i].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                    var session = courseID.substr(2, 3);
                                    if (s[i].getElementsByTagName("room").length !== 0) {
                                        room = s[i].getElementsByTagName("room")[0].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                                        if (s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith").length !== 0)
                                            room = room + " + " + s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith")[0].childNodes[0].nodeValue;
                                    }
                                }
                            }
                        }
                        else if (session !== "RWD")
                            friday += "<td></td>";
                        if (session === "SDJ") {
                            friday += "<td>" + startTime + "-" + endTime + "<br>" + teacherID + "<br>" + room + "</td>";
                            room = "";
                            if (i < s.length - 1) {
                                var dString = s[i + 1].getElementsByTagName("year")[0].childNodes[0].nodeValue + "," + s[i + 1].getElementsByTagName("month")[0].childNodes[0].nodeValue + "," + s[i + 1].getElementsByTagName("day")[0].childNodes[0].nodeValue;
                                const d = new Date(dString);
                                let weekday = d.getDay();
                                var courseID = s[i+1].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                var classLetter = courseID.substr(1, 1);
                                while(letter != classLetter && i<s.length-1)
                                {
                                    i++;
                                    var courseID = s[i+1].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                    var classLetter = courseID.substr(1, 1);
                                }
                                if (weekday == 5) {
                                    i++;
                                    var teacherID = s[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                                    var startTime = s[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[0].childNodes[0].nodeValue;
                                    var endTime = s[i].getElementsByTagName("hour")[1].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[1].childNodes[0].nodeValue;
                                    var courseID = s[i].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                    var session = courseID.substr(2, 3);
                                    if (s[i].getElementsByTagName("room").length !== 0) {
                                        room = s[i].getElementsByTagName("room")[0].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                                        if (s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith").length !== 0)
                                            room = room + " + " + s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith")[0].childNodes[0].nodeValue;
                                    }
                                }
                            }
                        }
                        else if (session !== "SDJ")
                            friday += "<td></td>";
                        if (session === "SEP") {
                            friday += "<td>" + startTime + "-" + endTime + "<br>" + teacherID + "<br>" + room + "</td>";
                            room = "";
                            if (i < s.length - 1) {
                                var dString = s[i + 1].getElementsByTagName("year")[0].childNodes[0].nodeValue + "," + s[i + 1].getElementsByTagName("month")[0].childNodes[0].nodeValue + "," + s[i + 1].getElementsByTagName("day")[0].childNodes[0].nodeValue;
                                const d = new Date(dString);
                                let weekday = d.getDay();
                                var courseID = s[i+1].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                var classLetter = courseID.substr(1, 1);
                                while(letter != classLetter && i<s.length-1)
                                {
                                    i++;
                                    var courseID = s[i+1].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                    var classLetter = courseID.substr(1, 1);
                                }
                                if (weekday == 5) {
                                    i++;
                                    var teacherID = s[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                                    var startTime = s[i].getElementsByTagName("hour")[0].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[0].childNodes[0].nodeValue;
                                    var endTime = s[i].getElementsByTagName("hour")[1].childNodes[0].nodeValue + ":" + s[i].getElementsByTagName("minute")[1].childNodes[0].nodeValue;
                                    var courseID = s[i].getElementsByTagName("courseID")[0].childNodes[0].nodeValue;
                                    var session = courseID.substr(2, 3);
                                    if (s[i].getElementsByTagName("room").length !== 0) {
                                        room = s[i].getElementsByTagName("room")[0].getElementsByTagName("id")[0].childNodes[0].nodeValue;
                                        if (s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith").length !== 0)
                                            room = room + " + " + s[i].getElementsByTagName("room")[0].getElementsByTagName("unitedWith")[0].childNodes[0].nodeValue;
                                    }
                                }
                            }
                        }
                        else if (session !== "SEP")
                            friday += "<td></td>";
                        friday += "</tr>";
                        break;
                    default: break;
                }
            }
        }
    }
    table += monday;
    table += tuesday;
    table += wednesday;
    table += thursday;
    table += friday;
    table += "</tbody>";
    document.getElementById("schedule").innerHTML = table;
}