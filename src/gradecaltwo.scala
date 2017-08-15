import scala.collection.mutable.ArrayBuffer

object GradecalTwo {
  def main(args: Array[String]){
    println("Welcome to the grade calculator version 2\nEnter your course details:")

    println("Enter your course name:")
    var courseName: String = scala.io.StdIn.readLine.toString()
    println("Now enter the semester you're taking this course:")
    var semesterDate: String = scala.io.StdIn.readLine.toString()
    println("Now enter your professor's name:")
    var professorName: String = scala.io.StdIn.readLine.toString()
    var courseObj: Course = new Course(courseName, semesterDate, professorName)

    println("How many sections does your course have:")
    var sectionNum: Integer = scala.io.StdIn.readLine.toInt
    for(i <- 1 to sectionNum ){
    println("Enter your section name")
    var sectionName: String = scala.io.StdIn.readLine.toString()
    println("Now your section's weight")
    var sectionWeight: Double = scala.io.StdIn.readLine.toDouble
    var sectionObj = new Section(sectionName, sectionWeight)
    courseObj.sectionList.append(sectionObj)
    }
    println("Here's what I have:")
    courseObj.displayInfo()

    println("Great now enter in your grades for each section in order")
    for (sec <- courseObj.sectionList){
    println("Displaying section: " + sec.n_)
    var isGradesFinish: Boolean = false
    while(!isGradesFinish){
        println("Enter the name of the assignment:")
        var gradeName = scala.io.StdIn.readLine.toString()
        println("Next enter your percentage: " )
        var gradePercent = scala.io.StdIn.readLine.toDouble
        var gradeObj = new Grade(gradeName, gradePercent)
        sec.gradeList.append(gradeObj)
        println("Would you like to enter another grade for this section (y/n)")
        var decision: Char = scala.io.StdIn.readChar()
        if(decision == 'n' || decision == 'N'){ isGradesFinish = true}
      } // End of while loop
    } // End of for loop
    println("Okay I'm calculating your grade together")
    for (sec <- courseObj.sectionList){
      println("The grade for " + sec.n_ + " is " + sec.calculateGrade(sec.gradeList))
      println("The weighted percentage is " + sec.calculateWeightedGrade(sec.gradeList, sec.w_))
    }// End of for loop
    println("Your course running average is " + courseObj.calculateRunningGrade(courseObj.sectionList))
  }// End of main method
}// End of main object

class Course (name:String, semester: String, professorName: String){
    var sectionList: ArrayBuffer[Section] = ArrayBuffer[Section]()

    def displayInfo(){
      println("Course Name: " + name)
      println("Course Semester: " + semester)
      println("Professor Name: " + professorName)
      for (sec <- sectionList){
        sec.display()
      }
    }

    def calculateRunningGrade(sections: ArrayBuffer[Section]): Double = {
      var courseAver: Double = 0
      for (sec <- sections){
        courseAver += sec.calculateWeightedGrade(sec.gradeList, sec.w_)
      }
      return courseAver
    }
}

class Section(name: String, weight: Double) {
  var n_ = this.name
  var w_ = this.weight
  var gradeList: ArrayBuffer[Grade] = ArrayBuffer[Grade]()

  def display(){
    println("Section Name: " + n_ + "\t Section Weight: " + w_)
  }
  def calculateGrade(grades: ArrayBuffer[Grade]): Double = {
    var sum: Double = 0
    var counter: Integer = 0
    for(g <- grades){
      sum += g.percentage
      counter += 1
    }
    return sum/counter
  }
  def calculateWeightedGrade(grades: ArrayBuffer[Grade], weight: Double): Double = {
    var average: Double = 0
    average = calculateGrade(grades).asInstanceOf[Double]
    return average * (weight / 100)
  }

}

class Grade (var name: String, var percentage: Double){

}
