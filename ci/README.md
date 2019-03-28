
##### what is it
+ ['open source thing doer'](https://concourse-ci.org/)
    - general approach to automation
+ server can be ran locally with docker, or configured on a vm running a worker, web, and postgresql node
    - clear out volumes when you're finished: `docker system prune --all --force --volumes`
+ uses the [fly cli](https://concourse-ci.org/fly.html) for communication to work with pipelines

##### fly login/setup
1. `fly --target local login --concourse-url http://localhost:8080`
    - authenticate with an endpoint
    - set an alias for your pipeline
    - creates `~/.flyrc` file with targets & 24hr token
    - default team name is main
1. `fly -t local set-team --team-name team-one --local-user test`
    - create team named `team-one`
1. `fly --target local login --team-name team-one --concourse-url http://localhost:8080`
    - login to a specific team name
1. `fly -t local builds`
    - lists last few builds for `local` instance
1. `fly targets`
    - shows each target's name, url, and token expiration
+ fly command is stateless - __always__ requires `-t` flag to specify instance
+ [useful commands](https://concourse-ci.org/fly.html)

##### pipelines
+ result of configuring jobs and resources together
+ pipeline can be composed of jobs, resources, resource_types, and groups
1. `fly -t local set-pipeline --pipeline sample-pipeline --config pipeline.yml`
    - create a pipeline named `sample-pipeline` using the `pipeline.yml` file
1. `fly -t local unpause-pipeline -p sample-pipeline`
    - unpause the pipeline that is paused by default

##### resources
1. represent all external inputs and outputs
    - git, pcf, nexus, etc.
1. [options](https://github.com/concourse?query=-resource)

##### jobs
+ determine the actions of your pipeline and how resources progress through it
+ determine how your pipeline is visualized
+ build plan will describe what a job does
 
1. `fly -t local jobs --pipeline sample-pipeline`
    - see all jobs for a pipeline
1. `fly -t local trigger-job --job sample-pipeline/test`
    - trigger specific job to start
1. `fly -t local pause-job --job sample-pipeline/test`
    - pause specific job from running
1. `fly -t local intercept -j sample-pipeline/test`
    - tunnel into job/docker container to debug failed test

##### tasks
+ smallest configurable unit
+ pure function that can pass/fail
 
1. `fly -t local execute --config task.yml`
    - runs a one off task

##### build
+ an execution of a build plan
+ one off result of `fly execute`

1. `fly -t local abort-build --job sample-pipeline/test --build 5`
    - abort a specific build for a job
