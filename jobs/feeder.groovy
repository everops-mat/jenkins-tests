folder('GeneratedJobs') {
    description('Folder for automatically generated jobs')
}

job('GeneratedJobs/job1') {
    description('This job is created from Job DSL')
    scm {
        git('https://github.com/everops-mat/dockerbuckets.git')
    }
    triggers {
        scm('H/5 * * * *')
    }
    steps {
        shell('echo "Executing job1"')
    }
}

job('GeneratedJobs/job2') {
    description('Another example job from Job DSL')
    scm {
        git('https://github.com/everops-mat/expectbucket.git')
    }
    triggers {
        scm('H/10 * * * *')
    }
    steps {
        shell('echo "Executing job2"')
    }
}
