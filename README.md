#Microsoft Academic Graph (MAG)

![image](https://studentclub.msra.cn/static/images/bop/2016/BOPPoster.jpg)

Microsoft Academic Graph (MAG) is a large heterogeneous graph containing entities such as authors, papers, journals, conferences and relations between them. Microsoft provides [Academic Knowledge API](https://www.microsoft.com/cognitive-services/en-us/academic-knowledge-api) for this contest. The Entity attributes are defined [here](https://www.microsoft.com/cognitive-services/en-us/academic-knowledge-api/documentation/EntityAttributes).

Participants are supposed to provide a REST service endpoint that can find all the 1-hop, 2-hop, and 3-hop graph paths connecting a given pair of entity identifiers in MAG. The given pair of entity identifiers could be [Id, Id], [Id, AA.AuId], [AA.AuId, Id], [AA.AuId, AA.AuId]. Each node of a path should be one of the following identifiers: Id, F.Fid, J.JId, C.CId, AA.AuId, AA.AfId. Possible edges (a pair of adjacent nodes) of a path are:

![image](https://studentclub.msra.cn/static/images/bop/2016/topic.png)

For each test case, the REST service endpoint will receive a JSON array via HTTP with a pair of entity identifiers, where the identifiers are 64-bit integers, e.g. [123, 456]. The service endpoint needs to respond with a JSON array within 300 seconds. The response JSON array consists of a list of graph paths in the form of [path1, path2, …, pathn], where each path is an array of entity identifiers. For example, if your program finds one 1-hop paths, two 2-hop paths, and one 3-hop paths, the results may look like this: [[123,456], [123,2,456], [123,3,456], [123,4,5,456]]. For a path such as [123,4,5,456], the integers are the identifiers of the entities on the path. After receiving the response, the evaluator will wait for a random period of time before sending the next requests.

Evaluation Metric
The REST service must be deployed to a [Standard_A3](https://www.azure.cn/home/features/virtual-machines/#price) virtual machine for the final test. There are no constraints on the programming language you can use.

The test cases are not available before the final evaluation. When the evaluation starts, the evaluator system sends test cases to the REST endpoint of each team individually. Each team will receive 10 test cases (Q1to Q10). The response time for test case Qi is recorded as Ti(1≤i≤10). The final score is calculated using:


![image](https://studentclub.msra.cn/static/images/bop/2016/score.png)

where Ni is the size of the solution (the total number of correct paths) for Qi , Ki is the total number of paths returned by the REST service, Mi is the number of distinct correct paths returned by the REST service.

***
微软学术图谱(Microsoft Academic Graph)(MAG)，是一个大型的，包含多样化内容的图（graph），这个图包含了包扩作者（authors）,论文（papers）,领域（journals）,会议（conferences）等等实体（entity）,以及它们之间的关系，我的理解类似于graph中的node。后面提供了MAG API以及Entity attributes的链接。题目要求：参赛者需要提供一个REST service endpoint，它能够获得给定的一对entity ID（例如[entity1, entity2]）之后，在MAG中找到所有从entity1到entity2的路径，要求长度为1，2，3，即一跳，二跳，三跳（1-hop,2-hop,3-hop）。给定的entity id对，可以是如下的形式 [Id, Id], [Id, AA.AuId], [AA.AuId, Id], [AA.AuId, AA.AuId]；路径中的每一个节点只能是如下的类型Id, F.Fid, J.JId, C.CId, AA.AuId, AA.AfId. —属性的名字含义详见Entityattributes的链接下面是所有可能的路径中边的示例输入输出规范：对于每个测试用例而言，the REST service endpoint将通过HTTP获得一个JSONarray的请求，这个数组储存了一对entity id，每个id是64-bit的integer，例如[123,456].servic endpoint需要在300s内输出一个JSON数组作为回应，这个数组中以[path1,path2,..]的形式储存所有从123，到456的一跳二跳三跳路径，每一个path是一个entity id的数组。例如，如果你的程序找到一个一条，2个二跳和一个三跳的路径，结果应该是如下的形式[[123,456],[123,2,456],[123,3,456],[123,4,5,456]]。对于其中的一个数组[123,4,5,456],其中每一个int代表着path上的node即一个entity id，在收到JSON数组的反馈之后，系统会在随机的一段时间之后再次发送请求。评估规则：在最后的测试中，the REST service必须部署在Standard_A3 的虚拟机上，对程序语言没有限制。在最后评估之前测试用例是不公开的。当评估开始，评估系统将会给每个队伍的REST endpoint发送不同的测试用例。每个队伍将受到Q1到Q10的10个测试用例，用例的回应时间将被记录在Ti中。最后的分数将按照如下的公式计算（见题目中）。Ni代表Qi中所有满足条件的path数目，Ki表示你回答的path总数，Mi表示你答对的且不重复的path总数。
