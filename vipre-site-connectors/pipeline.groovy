
job('vipre-site-connectors') {

    label('vs2017&&wix')
	description('')
	
	wrappers {
		colorizeOutput()
        timestamps()
        // https://issues.jenkins-ci.org/browse/JENKINS-34085
        /*versionNumberBuilder {
            versionNumberString('1.0.0')
            environmentVariableName('VERSION')
            useAsBuildDisplayName(true)
            projectStartDate('2012-05-04')
            environmentPrefixVariable(null)
            buildsToday(null)
            buildsThisWeek(null)
            buildsThisMonth(null)
            buildsThisYear(null)
            buildsAllTime(null)
            //skipFailedBuilds(false)
            worstResultForIncrement(null)
        }*/

    }

    configure {
            it / 'buildWrappers' / 'com.gfi.jenkins.versioning.VersionNumberCreator'(plugin: "versioning-plugin@1.1.0") {
                'major'(1)
                'minor'(1)
                'patch'(1)
                'revision'(10)
                'suffix'()
                'includePatchNumber'(true)
                'incrementOnFailure'(false)
            }
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
        
        /*changeassemblyversion {
            versionPattern('${VERSION_NUMBER}')
            assemblyFile()
        }*/

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