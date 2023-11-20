# Command-Line-Interpreter
CMD-like java program that allows you to do a variety of commands
<h2>Command List</h2>
<ol>
  <li><b>echo:</b> takes 1 or more arguments and prints them to the screen.</li>
  
  <li><b>pwd:</b> takes no arguments and prints the current path.</li>
  
  <li>
    <b>cd:</b> 
    <ul>
      <li> takes .. argument and changes the current directory to the parent directory.</li>
      <li> takes no arguments and changes the current directory to the user's home directory.</li>
      <li> takes 1 argument which is either the full path or the relative (short) path and changes the current directory to the given path.</li>
    </ul>
  </li>
  
  <li>
    <b>ls:</b> 
    <ul>
      <li> takes no arguments and prints the contents of the current directory.</li>
      <li> takes 1 argument "-r" and prints the contents of the current directory in reverse order.</li>
    </ul>
  </li>
  
  <li>
    <b>mkdir:</b> takes 1 or more arguments and creates a directory for each argument. Each argument can be: 
    <ul>
      <li> Directory name (in this case the new directory is created in the current directory).</li>
      <li> Path (full/short) that ends with a directory name (in this case the new directory is created in the given path).</li>
    </ul>
  </li>
  
  <li>
    <b>rmdir:</b>
    <ul>
      <li> takes 1 argument which is "*" (e.g. rmdir *) and removes all the empty directories in the current directory.</li>
      <li> takes 1 argument which is either the full path or the relative (short) path and removes the given directory only if it is empty.</li>
    </ul>
  </li>
      
  <li><b>touch:</b> takes 1 argument which is either the full path or the relative (short) path that ends with a file name and creates this file.</li>
  
  <li><b>cp:</b> takes 2 arguments, both are files and copies the first onto the second.</li>
  
  <li><b>rm:</b> takes 1 argument which is a file name that exists in the current directory and removes this file.</li>
  
  <li><b>cat:</b> takes 1 argument and prints the file's content or takes 2 arguments and concatenates the content of the 2 files and prints it.</li>
  
  <li>
    <b>wc:</b> takes file name as input and displays four-columnar output.
    <ul>
      <li> 1st column shows number of lines present in a file.</li> 
      <li> 2nd column shows number of words present in the file.</li> 
      <li> 3rd column shows number of characters present in file.</li> 
      <li> 4th column itself is the file name which are given as argument.</li>
    </ul>
  </li>

  <li><b>history:</b> takes no parameters and displays an enumerated list with the commands you've used in the current session.</li>

  <li><b>exit:</b> takes no parameters and quits the program</li>
</ol>
</br>


<h2>Command Structure</h2>
<h3>General Structure of a Command</h3> <p>Note: the "+" isn't written</p>
<pre>commandName + parameters</pre>

<p>The following commands however can have a different structure that allows you to save their output into a new file or append it to an existing file</p>

<h3>Eligible Commands:</h3>
<ul>
  <li>echo</li>
  <li>pwd</li>
  <li>ls</li>
  <li>wc</li>
  <li>history</li>
</ul>

<h3>Structure</h3>
<p>Saving content into a new file</p>
<pre>commandName + parameters + > + fileName</pre>

<p>Appending content into an existing file</p>
<pre>commandName + parameters + >> + fileName</pre>

