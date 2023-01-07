# Collections for parallel work
## TheAnalyzerProgram-CollectionsForParallelWork-Multithreading
### Description
Your colleague from the first task is still puzzling over mathematical statistics. Thanks to the generator already known to you, it creates 10_000 texts with a length
of 100_000 each from the characters "abc".
<details>
  <summary>Text Generator</summary>
  
  ```java
    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
  ```
</details>

At the same time, he is now interested in what the text would look like, which contains the maximum number of:
* characters 'a';
* characters 'b';
* characters 'c';
Let's try to solve this problem multithreaded: so that a separate thread is responsible for analyzing strings for the maximum number of each of the three characters. 
That is, one thread would be responsible for searching for the string with the largest number of characters 'a', the second for searching with the largest number of 
'b' and the third for 'c'.

However, it would be wrong to generate all the texts, save them in an array and then go through them, because in total there would be about 1 billion texts. characters,
which would lead to excessive memory consumption. We can go the other way and parallelize the string creation stage and the stages of their analysis.

To do this, the rows will be generated in a separate thread and fill blocking queues, the maximum size of which will be limited to 100 rows. Queues will need to be made 
one for each of the three analyzing threads, because the row must be processed by each such thread.

  ### Realization
1. Create three thread-safe blocking queues in static fields;
2. Create a stream that would fill these queues with texts;
3. Create a thread for each of the three characters 'a', 'b' and 'c', which would parse their turn and perform calculations.
  
  <a href="https://github.com/netology-code/jd-homeworks/blob/video/concurrent_collections/task1/README.md">(RUS version of description)</a>
  
  ### Result
  ![image](https://user-images.githubusercontent.com/63547457/211164480-5a8db74c-362f-47b3-94d6-d713f87d1cb1.png)
