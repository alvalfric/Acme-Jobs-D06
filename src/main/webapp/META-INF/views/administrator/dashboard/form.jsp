<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-integer code="administrator.dashboard.form.label.announcements" path="totalNumberOfAnnouncements" readonly="true" />
	<acme:form-integer code="administrator.dashboard.form.label.companyRecords" path="totalNumberOfCompanyRecords" readonly="true" />
	<acme:form-integer code="administrator.dashboard.form.label.investorRecords" path="totalNumberOfInvestorRecords" readonly="true" />
	<acme:form-integer code="administrator.dashboard.form.label.minActiveRequests" path="mininumRewardOfActiveRequests" readonly="true" />
	<acme:form-integer code="administrator.dashboard.form.label.maxActiveRequests" path="maximumRewardOfActiveRequests" readonly="true" />
	<acme:form-integer code="administrator.dashboard.form.label.avgActiveRequests" path="averageRewardOfActiveRequests" readonly="true" />
	<acme:form-integer code="administrator.dashboard.form.label.minOffers" path="mininumRewardOfActiveOffers" readonly="true" />
	<acme:form-integer code="administrator.dashboard.form.label.maxOffers" path="maximumRewardOfActiveOffers" readonly="true" />
	<acme:form-integer code="administrator.dashboard.form.label.avgOffers" path="averageRewardOfActiveOffers" readonly="true" />
</acme:form>
<b><acme:message code="administrator.dashboard.chart.label.companyInvestor"/></b>
<canvas id="myChart" width="250" height="50"></canvas>
<script>
	var ctx = document.getElementById("myChart").getContext('2d');
	var myChart = new Chart(ctx, {
		type : 'bar',
		data : {
			labels : [
				"<jstl:out value="${chartCompanyInvestor.get(0).get(0)}" escapeXml="false"/>",
				<jstl:forEach var="duty" items="${chartCompanyInvestor.get(0)}" begin="1">
					"<jstl:out value="${duty}" escapeXml="false"/>",
				</jstl:forEach>
			],
			datasets : [
				{
					data : [
						<jstl:out value="${Integer.parseInt(chartCompanyInvestor.get(1).get(0))}" escapeXml="false"/>
						<jstl:forEach var="duty" items="${chartCompanyInvestor.get(1)}" begin="1">
							,<jstl:out value="${Integer.parseInt(duty)}" escapeXml="false"/>
						</jstl:forEach>
					],
					label: 'Company Records/Registros de Compañias',
					backgroundColor : 'rgba(255, 99, 132, 0.2)',
					borderColor : 'rgba(255,99,132,1)',
					borderWidth : 1
				},
				{
					data : [
						<jstl:out value="${Integer.parseInt(chartCompanyInvestor.get(2).get(0))}" escapeXml="false"/>
						<jstl:forEach var="duty" items="${chartCompanyInvestor.get(2)}" begin="1">
							,<jstl:out value="${Integer.parseInt(duty)}" escapeXml="false"/>
						</jstl:forEach>
					],
					label: 'Investor Records/Registros de Inversores',
					backgroundColor : 'rgba(54, 162, 235, 0.2)',
					borderColor : 'rgba(54, 162, 235, 1)',
					borderWidth : 1
				}
			]
		},
		options : {
			scales : {
				yAxes : [
					{
						ticks : {
							suggestedMin : 0.0,
							suggestedMax : 1.0
						}
					}
				]
			},
			legend : {
				display : true
			}
		}
	});
</script>