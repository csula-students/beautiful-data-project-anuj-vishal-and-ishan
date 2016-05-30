package edu.csula.datascience.acquisition;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.node.Node;

import com.google.gson.Gson;

public class ElasticSearchExampleApp {
	private final static String indexName = "bd-data25";
	private final static String typeName = "test25";

	public static void main(String[] args) throws URISyntaxException, IOException {
		Node node = nodeBuilder()
				.settings(Settings.builder().put("cluster.name", "ishan").put("path.home", "elasticsearch-data"))
				.node();
		Client client = node.client();
		File csv = new File(ClassLoader.getSystemResource("test15.csv").toURI());
		BulkProcessor bulkProcessor = BulkProcessor.builder(client, new BulkProcessor.Listener() {
			@Override
			public void beforeBulk(long executionId, BulkRequest request) {
			}

			@Override
			public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {
			}

			@Override
			public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
				System.out.println("Facing error while importing data to elastic search");
				failure.printStackTrace();
			}
		}).setBulkActions(10000).setBulkSize(new ByteSizeValue(1, ByteSizeUnit.GB))
				.setFlushInterval(TimeValue.timeValueSeconds(5)).setConcurrentRequests(1)
				.setBackoffPolicy(BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3)).build();

		Gson gson = new Gson();

		try {
			CSVParser parser = CSVParser.parse(csv, Charset.defaultCharset(), CSVFormat.EXCEL.withHeader());
			parser.forEach(record -> {
				if (!record.get("Description").isEmpty() && !record.get("code").isEmpty()) {
					Temperature temp = null;
					try {

						temp = new Temperature((record.get("Violation_Time")), ((record.get("Violation_County"))),
								record.get("Plate_Type"), record.get("Plate_Id"), Integer.parseInt(record.get("code")),
								record.get("Description"), record.get("Registration_state")

						);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
						e.printStackTrace();
					}

					bulkProcessor.add(new IndexRequest(indexName, typeName).source(gson.toJson(temp)));
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static class Temperature {

		final String violationTime;
		final String issueDate;
		final String plateType;
		final String plateId;
		final int violationCode;
		final String violationDescription;
		final String registrationState;

		public Temperature(String violationTime, String Violation_county, String plateType, String plateId, int code,
				String violationDescription, String registrationState) {

			this.issueDate = Violation_county;
			this.violationTime = violationTime;
			this.plateType = plateType;
			this.plateId = plateId;
			this.violationCode = code;
			this.violationDescription = violationDescription;
			this.registrationState = registrationState;
		}
	}
}
