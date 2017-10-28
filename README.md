# [projects](http://ivans-innovation-lab.github.io/projects)/my-company-team-domain [![CircleCI](https://circleci.com/gh/ivans-innovation-lab/my-company-team-domain.svg?style=svg)](https://circleci.com/gh/ivans-innovation-lab/my-company-team-domain) [![release](http://github-release-version.herokuapp.com/github/ivans-innovation-lab/my-company-team-domain/release.svg?style=flat)](https://github.com/ivans-innovation-lab/my-company-team-domain/releases/latest)

This component processes commands. Commands are actions which change state in some way. The execution of these commands results in Events being generated which are persisted by Axon, and propagated out to other components (possibly on other VMs). In event-sourcing, events are the sole records in the system. They are used by the system to describe and re-build domain aggregate (`TeamAggregate.java`) on demand, one event at a time.

## Development

This project is driven using [Maven][mvn].

[mvn]: https://maven.apache.org/

### Run/Install locally
 
Make sure that you have this libraries installed in your local maven repository:

 - [my-company-common-team](https://github.com/ivans-innovation-lab/my-company-common/tree/master/my-company-common-team)
 - [my-company-project-domain](https://github.com/ivans-innovation-lab/my-company-project-domain)

```bash
$ ./mvnw clean install
```

### Run tests

This component comes with tests. Use the following command to execute the tests using Maven:

```bash
$ ./mvnw test
```

---
Created by [Ivan Dugalic][idugalic]@[lab][lab].
Need Help?  [Join our Slack team][slack].

[idugalic]: http://idugalic.pro
[lab]: http://lab.idugalic.pro
[slack]: https://join.slack.com/t/idugalic/signup
