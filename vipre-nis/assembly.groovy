
println()
println(WORKSPACE)

def vipreNisDir = WORKSPACE + File.separator + "vipre-nis"

println(vipreNisDir)

//evaluate(new File(vipreNisDir + File.separator + "job1.groovy"))
//evaluate(new File(vipreNisDir + File.separator + "job2.groovy"))

def vipreRPT = "VipreRulePackagerTest.groovy"
def vipreRP "VipreRulePackager.groovy"

def vipreNisRPTest = 'viprenis-rules-packager-fortest'
def vipreNisRP = 'viprenis-rules-packager'

File sourceFile = new File(vipreNisDir + File.separator + "src" + File.separator + vipreRPT )
Class TemplatesDSL = new GroovyClassLoader(getClass().getClassLoader()).parseClass(sourceFile)

def myJob = job(vipreNisRPTest)
TemplatesDSL.generate(myJob)

sourceFile = new File(vipreNisDir + File.separator + "src" + File.separator + vipreRP )
TemplatesDSL = new GroovyClassLoader(getClass().getClassLoader()).parseClass(sourceFile)

myJob = job(vipreNisRP)
TemplatesDSL.generate(myJob)

listView('githubView') {
    jobs {
      name(vipreNisRP)
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