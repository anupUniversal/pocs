class VipreRulePackager {
    static void generate(def job) {
        job.with {
            wrappers {
                colorizeOutput()
                timestamps()	
            }

            publishers {
                extendedEmail {
                    recipientList('omigd33@gmail.com')
                    defaultSubject('$DEFAULT_SUBJECT')
                    defaultContent('$DEFAULT_CONTENT')
                    attachBuildLog(true)
                }
            }
        }
    }
}