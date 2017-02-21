# Imports the monkeyrunner modules used by this program
from com.android.monkeyrunner import MonkeyRunner, MonkeyDevice
import time

# Connects to the current device, returning a MonkeyDevice object
device = MonkeyRunner.waitForConnection()

# Installs the Android package. Notice that this method returns a boolean, so you can test
# to see if the installation worked.
device.installPackage('../app/build/outputs/apk/app-debug.apk')

# sets a variable with the package's internal name
package = 'de.frost.android.todoandroidjunitrunner'

# sets a variable with the name of an Activity in the package
activity = 'de.frost.android.todoandroidjunitrunner.MainActivity'

# sets the name of the component to start
runComponent = package + '/' + activity

# Runs the component
device.startActivity(component=runComponent)

time.sleep(5)

# Presses the Menu button
device.touch(100, 1700, MonkeyDevice.DOWN_AND_UP)
time.sleep(1)
device.type("monkeyrunner_testing!")
time.sleep(1)
device.touch(100, 1700, MonkeyDevice.DOWN_AND_UP)
time.sleep(1)
# Takes a screenshot
result = device.takeSnapshot()

# Writes the screenshot to a file
result.writeToFile('shot2.png','png')
