# ΕΔΕΤ Relay Web Service 

Υλοποιήθηκε ένας [Web Service Client σαν REST Web Service που εκτελείται σε Java application server](Academicservice.java) για την κατανάλωση του Service Ελέγχου Ακαδημαικής Ταυτότητας του ΕΔΕΤ μέσω php curl, η απλής HTTP GET σύνδεσης από γλώσσα προγραμματισμού υψηλού επιπέδου. Οι λειτουργίες του είναι:

* queryID, που επιστρέφει *την απάντηση του επίσημου web service*, με παραμέτρους:
    * username υποχρεωτικό, το username του επιθεωρητή που είναι καταχωρημένος στο authentication του web service του ΕΔΕΤ
    * password υποχρεωτικό, το password του επιθεωρητή που είναι καταχωρημένος στο authentication του web service του ΕΔΕΤ
    * identity υποχρεωτικό, το 12 ψήφιο academic id του φοιτητή προς έλεγχο
* queryIDnoCD που επιστρέφει *κείμενο isStudent:[true,false]* όπως προκύπτει με φιλτράρισμα του JSON response του επίσημου web service, με παραμέτρους:
    * identity υποχρεωτικό, το 12 ψήφιο academic id του φοιτητή προς έλεγχο
* testServiceStatus, που επιστρέφει το query_string, με οποιαδήποτε παράμετρο
