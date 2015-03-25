package ch.claimer.webservice.service;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

public class JsonDataProcessorService<T> implements DataProcessorService<T> {
	
	private final ObjectMapper mapper = new ObjectMapper();

	@Override
	public T read(String data, Class<T> clazz) {
		T t = null;
		try {
			t = mapper.readValue(data, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return t;
	}

	@Override
	public String write(T t) {
		String json = "";
		try {
			json = mapper.writeValueAsString(t);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}

	@Override
	public String write(List<T> list) {
		String json = "";
		try {
			json = mapper.writeValueAsString(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}

}
