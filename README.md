# my-company-team-domain 

This component processes commands. Commands are actions which change state in some way. The execution of these commands results in Events being generated which are persisted by Axon, and propagated out to other components (possibly on other VMs). In event-sourcing, events are the sole records in the system. They are used by the system to describe and re-build domain aggregate (`Team.java`) on demand, one event at a time.

## Development

This project is driven using [Maven][mvn].

[mvn]: https://maven.apache.org/

### Run/Install locally
 
Make sure that you have this libraries installed in your local maven repository:

 - [my-company-common](https://github.com/ivans-innovation-lab/my-company-common)

```bash
$ ./mvnw clean install
```

### Run tests

This component comes with some rudimentary tests as a good starting
point for writing your own.  Use the following command to execute the
tests using Maven:

```bash
$ ./mvnw test
```

---
Created by [Ivan Dugalic][idugalic]@[lab][lab].
Need Help?  [Join our Slack team][slack].

[idugalic]: http://idugalic.pro
[lab]: http://lab.idugalic.pro
[slack]: https://join.slack.com/t/idugalic/signup
