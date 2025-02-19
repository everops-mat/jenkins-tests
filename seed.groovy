
import jenkins.model.*
import hudson.model.*
import javaposse.jobdsl.plugin.ExecuteDslScripts

// Get Jenkins instance
def instance = Jenkins.getInstance()

// Define the job name
def jobName = "JobFeeder"

if (instance.getItem(jobName) == null) {
    println "Creating Seed Job: $jobName"

    def job = instance.createProject(FreeStyleProject, jobName)

    def dslScript = '''
        job('example-job') {
            description('An example job created by Job DSL')
            scm {
                git('https://github.com/everops-mat/jenkins-tests.git')
            }
            triggers {
                scm('H/5 * * * *')
            }
            steps {
                shell('echo "Running example job"')
            }
        }
    '''

    def dslBuilder = new ExecuteDslScripts()
    dslBuilder.setTargets('jobs/*.groovy')
    dslBuilder.setUseScriptText(false)
    dslBuilder.setScriptText(dslScript)

    job.getBuildersList().add(dslBuilder)
    job.save()
    println "Seed Job created successfully!"
} else {
    println "Seed Job already exists."
}
