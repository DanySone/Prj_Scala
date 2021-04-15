# Projet Scala - Peaceland
## Situation
### Client
Peaceland is a country that takes great pride in its effort to bring peace,
happyness, and harmony to all its citizens.
To do so they heavily rely on their peacemakers. A governmental agency
dedicated to make peace around the country. To do so they bring assistance to
any agitated person and help them to recover peace. More generally they help
citizen to stay in line with their country harmonious goal
To help its peacemakers squads, Peaceland engineers have created a working
autonomous drone called peacewatcher.
They need you to create the program that will receive and manage
peacewatchers’s data.
This program must
- store every peacewatcher data
- trigger alerts
- enable peacemaker officers to performs analysis on peacewatcher data
### Drone description
Each peacewatcher sends a report every minute.
One report contains
- peacewatcher id
- peacewatcher current location (latitude, longitude)
- name of surrounding citizen (identify with facial recognition) with their
computed «peacescore»
- words heard by the peacewatcher in its surrounding
### Alert
When a citizen peacescore is bad your program must trigger an alert with
location of the peacewatcher and the name of the agitated citizen.
Peacemakers will take it from there and help the person to find peace.
They may send him to a peacecamp. In such camb citizen learn to reach
happiness following the ideas of the beneveland leader of Peaceland. Or they
will put him in a sustainable and never ending peace state.
This alert must be triggered as quickly as possible because an agitated citizen
may spread its lack of peace to other citizens. Thus the peacemaker reaction
must be as fast as possible.
### Statistics
Peacemakers are convinced that we need to keep every peacemaker report in
order to make statistics and improve their Peaceland harmony. But they still don’t
know what kind of question/statistic they will want to address.
Peaceland engineer estimate that when the first wave of peacewatcher will be
operational the sum of all there daily report will weight 200Gb
### Failed attempt
To create a POC of the program Peaceland hired a team of data-scientists and
Despite all their efforts, this team have not been able to set up scalable program
that can handle the load.
## Preliminary questions
1) What technical/business constraints should the data storage component of the
program architecture meet to fulfill the requirement described by the customer in
paragraph « Statistics» ?
So what kind of component(s) (listed in the lecture) will the architecture need?
2) What business constraint should the architecture meet to fulfill the requirement
describe in the paragraph «Alert»
3) What mistake(s) from Peaceland can explains the failed attempt?
4) Peaceland has likely forgotten some technical information in the report sent by the
drone. In the future this information could help Peaceland make its peacewatchers
much more efficient. Which information?
## Project
Peaceland understands this is beyond their team limits, it can not put in place a programm to
deal with the drone’s data. Peaceland asks you for advice to design an architecture allowing
them to create a product they could sell to different police forces.
It's up to you to report and recommend the right architecture.
Based on the preliminary question your solution is very likely to include :
  - at least one distributed storage
  - at least one distributed stream
  - at least two stream consumer
