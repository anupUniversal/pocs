
println()
println(WORKSPACE)

def vipreNisDir = WORKSPACE + File.separator + "vipre-nis"

println(vipreNisDir)

//evaluate(new File(vipreNisDir + File.separator + "job1.groovy"))
//evaluate(new File(vipreNisDir + File.separator + "job2.groovy"))

File sourceFile = new File(vipreNisDir + File.separator + src + File.separator + "VipreRulePackagerTest.groovy")
Class TemplatesDSL = new GroovyClassLoader(getClass().getClassLoader()).parseClass(sourceFile)

def vipreNisRPTest = 'viprenis-rules-packager-fortest'

def myJob = job(vipreNisRPTest)
TemplatesDSL.generate(myJob)

listView('githubView') {
    jobs {
      name('viprenis-rule-packager')
      name(vipreNisRPTest)
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