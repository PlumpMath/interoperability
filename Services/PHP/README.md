# Δείγμα Web Sercice Client 

Υλοποιήθηκε ένας [δοκιμαστικός Web Service Client σε PHP application server](wrapper.php) για την κατανάλωση του Service Ελέγχου Ακαδημαικής Ταυτότητας του ΕΔΕΤ μέσω php curl. Οι λειτουργίες του είναι:

* queryID, που επιστρέφει την απάντηση του επίσημου web service, με παραμέτρους:
* * username προεραιτικό, το username για το web service
* * password προεραιτικό, το password για το web service
* * identity υποχρεωτικό, το academic id για έλεγχο
* queryIDis που επιστρέφει κείμενο isStudent:[true,false] όπως προκύπτει από το επίσημο web service, με παραμέτρους:
* * username προεραιτικό, το username για το web service
* * password προεραιτικό, το password για το web service
* * identity υποχρεωτικό, το academic id για έλεγχο
* * echo, που επιστρέφει το query_string, με οποιαδήποτε παράμετρο

Οι λειτουργίες δίνονται επίσης ως παράμετρος κατά την κλήση. Για παράδειγμα:

```
http://local.dev/academic-id/wrapper.php?identity=123456789012 (default is queryID) 
http://local.dev/academic-id/wrapper.php?username=spapad&identity=123456789012&operation=queryIDis
http://local.dev/academic-id/wrapper.php?operation=echo&identity=123456789012
```

Το αρχείο [.htaccess](.htaccess) μετασχηματίζει τα εισερχόμενα αιτήματα σε μορφή κατάλληλη για το php application, ώστε να υπάρχει πλήρης αντιστοιχία στα endpoints με τον Web Service Client σε Java. Εναλλακτικά θα μπορούσε να χρησιμοποιηθεί ένα framework όπως το SLIM.

Οι λειτουργίες είναι: 

* testServiceStatus,
* queryIDnoCD,
* queryID
