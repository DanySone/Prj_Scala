**Preliminary questions**

**1) What technical/business constraints should the data storage component of the**

**program architecture meets to fulfill the requirement described by the customer in**

**paragraph « \*Statistics»?\* So, what kind of component(s) (listed in the lecture) will the architecture need?**

The customer wants to store the data to make statistics later, but they still do not know. Then, we need to store the data for an indeterminate duration. Also, we know that the sum of the weight of all daily reports is 200Go, we can conclude that the data storage must be a data lake (can store forever the data and good for huge data to store).

Moreover, from a financial view it is interesting to use a data lake: for example, the price per month of the Azure Data Lake Gen1 is €0.0329/GB/month. After calculation peacemakers will pay €14,000 the first year and €54,000 the next year (without price discount). Finally, we can say that these prices are affordable for a governmental agency. 

 

**2) What business constraint should the architecture meet to fulfill the requirement**

**describe in the paragraph «Alert»?**

The first business constraint is the time, indeed when the alert is triggered, the bad citizen must be sent to a peacecamp as fast as possible to be at peace. The second business constraint is financial, peacemakers need to react fast, so we need a lot of peacewatchers to make the task easier for the peacemakers.

**3) What mistake(s) from Peaceland can explains the failed attempt?**

The mistake is the fact that Peaceland hire a data-scientist team which are not qualified to this type of task. They are supposed to build machine learning models and make predictions, here the question is to design the architecture and create scalable programs.

**4) Peaceland has likely forgotten some technical information in the report sent by the**

**drone. In the future this information could help Peaceland make its peacewatchers**

**much more efficient. Which information?**

They forgot to put a date on the collected data. It will be interesting to keep the date of the information especially when we will make some statistics in the future. 

 