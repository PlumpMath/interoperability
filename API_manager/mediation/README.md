
# Κατανάλωση του Academic ID service με mediation από τον API manager

Η κατανάλωση μπορεί να γίνει και με mediation από τον API manager. 
Σε ένα απλό σενάριο χρήσης ο mediator μπορεί να θέσει το εξειδικευμένο 
authentication header και να παράγει το απαραίτητο μήνυμα για αποστολή.

## Mediation sequence

Το [mediation sequence](sequence.xml) υλοποιεί ένα απλό σενάριο κλήσης 
στο οποίο πραγματοποιούνται οι λειτουργίες: 

1.  Κλήση με class mediator του custom mediator `gr.gov.minedu.osteam.AcademicIdentityAuthMediator` ο οποίος κατασκευάζει το εξειδικευμένο authentication header 
2.  Καθορισμός του Authorization header στο μήνυμα 
3.  Κατασκευή του payload - json message που παίρνει από το path το identity για το οποίο γίνεται η κλήση 
4.  Ορισμός μεθόδου σε POST
5.  Καθαρισμός του URL για την τελική κλήση

```xml
<?xml version="1.0" encoding="UTF-8"?>
<sequence name="acad05" trace="disable" xmlns="http://ws.apache.org/ns/synapse">
  <class description="calculate auth" name="gr.gov.minedu.osteam.AcademicIdentityAuthMediator"/>
  <property name="messageType" scope="axis2" type="STRING" value="application/json"/>
  <property description="Set authorization"
   expression="get-property('auth')" name="Authorization"
   scope="transport" type="STRING"/>
  <payloadFactory description="" media-type="json">
    <format>{ "SubmissionCode": "$1" }</format>
    <args>
      <arg evaluator="xml" expression="$ctx:uri.var.identity"/>
    </args>
  </payloadFactory>
  <property description="Set request method to POST" name="HTTP_METHOD"
    scope="axis2" type="STRING" value="POST"/>
  <property description="Clear postfix" name="REST_URL_POSTFIX"
    scope="axis2" type="STRING" value=""/>
  <log level="full"/>
</sequence>
```

## Προσαρμοσμένος mediator 

Στο αρχείο [AcademicIdentityAuthMediator.java](AcademicIdentityAuthMediator.java) παρατίθεται δείγμα κώδικα για τον προσαρμοσμένο mediator.

