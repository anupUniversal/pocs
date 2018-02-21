

class VipreRulePackagerTest {
    static void generate(def job) {
        job.with {
            wrappers {
                colorizeOutput()
                timestamps()	
            }
            
            triggers {
                scm('H(0-59)/15 * * * *')
            }


        publishers {
                extendedEmail {
                    recipientList('reginald.wong@vipre.com')
                    defaultSubject('$DEFAULT_SUBJECT')
                    defaultContent('$DEFAULT_CONTENT')
                    attachBuildLog(true)
                }
            }
        }
    }
}