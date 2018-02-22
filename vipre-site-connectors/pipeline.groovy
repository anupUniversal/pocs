
job('vipre-site-connectors') {

    label('vs2017&&wix')
	description('')
	
	wrappers {
		colorizeOutput()
        timestamps()
        deliveryPipelineVersion('1.0.${BUILD_NUMBER}', true)
    }
	
	multiscm {
        git {
            remote {
				url('https://bitbucket.org/threattrack/vipre-site-connectors.git')
            }
			branch('*/master')
        }
    }

    steps {
        msBuild {
            msBuildInstallation('MSBuild 1.28')
            buildFile('source/VipreConnectors.sln')
            args()
            passBuildVariables()
            continueOnBuildFailure()
            unstableIfWarnings()
        }
    }
	
   publishers {
        extendedEmail {
            recipientList('Zachary.Scolaro@vipre.com')
            recipientList('Nelson.Toro@jalasoft.com')
            recipientList('Marcel.Morales@jalasoft.com')
            defaultSubject('$DEFAULT_SUBJECT')
            defaultContent('$DEFAULT_CONTENT')
			attachBuildLog(true)
        }
    }
}

listView('vipre-site-connectors-view') {
    jobs {
      name('vipre-site-connectors')
      name('vipre-site-connectors-seed')
    }
  
   columns {
        status()
        weather()
        name()
        lastSuccess()
        lastFailure()
        lastDuration()
        buildButton()
    }
}